#!/bin/bash

parent_dir=$1

for x in $parent_dir/*.zip; do
	base_name=$(basename "$x" .zip)
	dir_name=p$base_name
	mkdir $parent_dir/$dir_name && mv $x $parent_dir/$dir_name
	unzip "${parent_dir}/${dir_name}/${base_name}.zip" -d "${parent_dir}/${dir_name}"
	rm "${parent_dir}/${dir_name}/${base_name}.zip"
	for y in $parent_dir/$dir_name/*.java; do
		sed -i '' '/^package/ d' $y
		sed -i '' "1s/^/package ${dir_name};/" $y
	done	
done