#! /bin/bash
mvn clean package -Dmaven.test.skip=true
JAR=`ls ./restj-rest/target/ | grep jar | head -n 1`
scp ./restj-rest/target/${JAR} louchen@45.32.90.134:/home/louchen/
