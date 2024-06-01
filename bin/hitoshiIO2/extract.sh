#!/bin/bash
ct=0
mkdir ./data/
for filename in ./codejam-2016-qua-q1/*.zip; do
    echo "$filename"    
    echo "$ct"
    ct=$((ct+1))
    unzip -o $filename -d data/$ct/
done
