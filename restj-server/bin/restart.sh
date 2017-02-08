#! /bin/bash

#app parameter
APP=restj-server
MAIN=person.louchen.restj.server.StartApp

#stop
ps -ef|grep ${APP}|grep -v grep|awk '{print $2}'|xargs kill -9

#start
nohup java -server -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Djava.ext.dirs=../lib -Dwebapp.path=../ ${MAIN} -app=${APP} &
