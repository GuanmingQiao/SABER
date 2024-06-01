#!/usr/env/bin python3

import argparse
import os
import json
import re
from jsonschema import validate
from collections import defaultdict


codebase_path = ''
json_schema = {
    "type" : "array",
    "items" : {
        "type": "object",
        "properties": {
            "filter_type": {
                "type": "string",
                "enum": ["search", "replace", "exclude"]
            },
            "filter_parameters": {
                "type": "array",
                "items": {
                    "type": "string"
                }
            }
        }
    }
}



def traverse_directory(dir_path, filters, meth_args):
    #print('traversing %s' % dir_path)
    for file in os.listdir(dir_path):
        #print('Checking file %s' % file)
        if os.path.isdir('%s/%s' % (dir_path, file)):
            traverse_directory('%s/%s' % (dir_path, file), filters, meth_args)

        if file.endswith('.java'):
            # apply filters
            #print('File %s ends with .java' % file)
            acceptable_file = True

            if filters:
                for search_regex in filters['search']:
                    #print("search_regex %s" % search_regex)
                    regex_in = False
                    with open('%s/%s' % (dir_path, file)) as java_file:
                        for line in java_file:
                            if re.search(search_regex, line):
                                regex_in = True
                                break

                    if not regex_in:
                        acceptable_file= False
                        break

                for exclude_regex in filters['exclude']:
                    regex_in = False
                    with open('%s/%s' % (dir_path, file)) as java_file:
                        for line in java_file:
                            if re.search(exclude_regex, line):
                                regex_in = True
                                break

                    if regex_in:
                        acceptable_file= False
                        break

                if filters['replace']:
                    with open('%s/%s' % (dir_path, file)) as java_file:
                        new_file = java_file.readlines()

                    # apply all replacements
                    for replace_regex, replacement in filters['replace']:
                        for i in range(len(new_file)):
                            new_file[i] = re.sub(replace_regex,
                                                 replacement,
                                                 new_file[i])

                    # overwrite with replacement
                    with open('%s/%s' % (dir_path, file), 'w') as java_file:
                        java_file.seek(0)
                        java_file.write(''.join(new_file))
                #print("acceptable_file %r" % acceptable_file)

            if not acceptable_file:
                continue

            has_java_files = True
            print('javac %s/%s' % (dir_path, file))
            os.system('javac %s/%s' % (dir_path, file))

    # execute step 2 of HitoshiIO2 on all .class files in bytecodebase
    for file in os.listdir(dir_path):
        if file.endswith('.class'):
            class_file = '%s.%s' % (dir_path.replace(codebase_path,
                                                    '').replace('/', '.'),
                                   file.replace('.class', ''))
            class_file = class_file.strip('.')
            

            output = (('java -javaagent:target/CloneDetector-0.0.1-SNAPSHOT.jar'
                       ' -noverify -cp "target/CloneDetector-0.0.1-SNAPSHOT.jar'
                       ':{}'.format(codebase_path) + '" edu.columbia.cs.psl.ioclones.driver'
                       '.IODriver %s %s' % (class_file, ' '.join(meth_args))))
            print(output)
            os.system(output)


if __name__ == '__main__':
    # get arguments
    parser = argparse.ArgumentParser(description=('Compile into JVM bytecode '
                                                  'and run HitoshiIO step II '
                                                  'on all .java files '
                                                  '(recursively) in a '
                                                  'directory, applying search, '
                                                  'exclude, and replacement '
                                                  'filters if specified in a '
                                                  'JSON file.'))

    parser.add_argument('codebase_path', type=str)
    parser.add_argument('--schema', dest='json_schema', type=str,
                        default='')
    parser.add_argument('meth_args', type=str, default=[],
                        nargs=argparse.REMAINDER)

    args = parser.parse_args()
    codebase_path = args.codebase_path
    meth_args = args.meth_args

    filters = defaultdict(list)
    if args.json_schema != '':
        try:
            with open(args.json_schema) as fp:
                json_filters = json.load(fp)
                validate(json_filters, json_schema)

            for single_filter in json_filters:
                if single_filter['filter_type'] == 'search':
                    params = single_filter['filter_parameters']
                    if len(params) != 1:
                        raise RuntimeError(('Search filter takes in 1 regular '
                                            'expression as a parameter. All '
                                            'search filters are treated as '
                                            'AND-type filters, so OR-type '
                                            'logic must be implemented in the '
                                            'regular expression.'))
                    filters['search'].append(params[0])
                elif single_filter['filter_type'] == 'replace':
                    params = single_filter['filter_parameters']
                    if len(params) != 2:
                        raise RuntimeError(('Replace filter takes in 2 '
                                            'parameters: a regular expression '
                                            'to search for, and a replacement '
                                            'string to be used in re.sub.'))
                    filters['replace'].append(params)
                elif single_filter['filter_type'] == 'exclude':
                    params = single_filter['filter_parameters']
                    if len(params) != 1:
                        raise RuntimeError(('Exclude filter takes in 1 regular '
                                            'expression as a parameter. All '
                                            'exclude filters are treated as '
                                            'OR-type filters, so AND-type '
                                            'logic must be implemented in the '
                                            'regular expression.'))
                    filters['exclude'].append(params[0])

        except:
            raise RuntimeError('Invalid JSON Schema for filtering')

    traverse_directory(codebase_path, filters, meth_args)
