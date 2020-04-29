#!/bin/bash

cat $1 | while read line 
do
  echo "docker images name is :${line}"
  #docker pull ${line}
  sub_name=${line//// };
  name=($sub_name)
  len=${#name[@]}
  echo "length $len"
  pkg=${name[$len-1]}".tar"
  echo "docker pull $line"
  docker pull $line
done
