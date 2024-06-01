#!/bin/bash

dir_name=$1
find $dir_name -type f -name "*.java" -exec sed -i '' "s/new Scanner(new File(\".*\"))/new Scanner(System.in)/g" {} \;
find $dir_name -type f -name "*.java" -exec sed -i '' "s/new Scanner(new FileReader(\".*\"))/new Scanner(System.in)/g" {} \;
find $dir_name -type f -name "*.java" -exec sed -i '' "s/new BufferedReader(new File(\".*\"))/new BufferedReader(System.in)/g" {} \;
find $dir_name -type f -name "*.java" -exec sed -i '' "s/new BufferedReader(new FileReader(\".*\"))/new BufferedReader(System.in)/g" {} \;

for d in $dir_name/*; do 
	should_remove=true
	for f in $d/*.java; do
		if grep -q "System.in" $f; then
			should_remove=false
		fi
	done
	if [ "$should_remove" = true ] ; then
		rm -r $d/
	fi
done