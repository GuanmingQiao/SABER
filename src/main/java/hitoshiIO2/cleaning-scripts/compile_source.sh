#!/bin/bash

parent_dir=$1

for d in $parent_dir/*; do 
	javac $d/*.java
	retVal=$?
	if [ $retVal -ne 0 ]; then
		rm -r $d/
	else
		rm $d/*.java
	fi
done
