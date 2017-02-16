#! /bin/bash
JAR=`ls ./restj-rest/target/ | grep jar | head -n 1`
nohup java -jar ./${JAR} --spring.profiles.active=prod -server