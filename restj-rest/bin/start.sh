#! /bin/bash
JAR=`ls ./ | grep jar`
nohup java -jar ./${JAR} &