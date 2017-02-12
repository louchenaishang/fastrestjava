#! /bin/bash
mvn clean package -Dmaven.test.skip=true -Pproduct
scp ./restj-server/target/release-restj-server.tar.gz louchen@45.32.90.134:/home/louchen/
