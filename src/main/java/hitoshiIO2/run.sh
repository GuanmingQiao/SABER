#!/bin/bash
path=./data/data
for filename in $path/*/*.java; do
	echo $filename
	find $path -type f -name "*.java" -exec sed -i '' 's/package.*;//g' {} \;
	javac $filename
done
for filename in $path/*/*.class; do
	i=$((i+1))
	filename=${filename%".class"}
	dir=${filename%/*}
	filename=${filename#"$dir/"} 
	#java -javaagent:target/CloneDetector-0.0.1-SNAPSHOT.jar -noverify -cp "target/CloneDetector-0.0.1-SNAPSHOT.jar:$dir" edu.columbia.cs.psl.ioclones.driver.IODriver $filename ./A-large.in >/dev/null
done
