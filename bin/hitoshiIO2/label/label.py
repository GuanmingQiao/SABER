"""
This script is used to compare HitoshiIO's result with dataset_clones.csv
to analyze its performance.

Example:
    $ python label.py /path/to/result.csv
"""

import csv
import sys

labels = {}

result = sys.argv[1]

with open('dataset_clones.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            line_count += 1
        else:
            print(row[0] + ":" + row[1])
            labels[row[0]] = row[1]
            line_count += 1

line_count = 0
with open(result) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    tp = 0
    for row in csv_reader:
        if line_count == 0:
            line_count += 1
        else:
            print(line_count)
            if labels[row[0]] == labels[row[2]]:
                tp += 1
            line_count += 1



line_count -= 1
print("captured clones = " + str(line_count) + " true clones = " + str(tp) + 
    " precision " + str(float(tp) / float(line_count)) + " recall = " + str(float(tp) / 108.0))
