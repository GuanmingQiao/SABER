#!/bin/bash

dir_name=$1
BASEDIR=$(dirname "$0")

$BASEDIR/preprocess_source.sh $dir_name
$BASEDIR/regularize_input.sh $dir_name
$BASEDIR/compile_source.sh $dir_name