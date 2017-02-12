 ## rest java      
 
 ### 1. 项目结构
 - restj-framework:此模块包含一些常用的开发工具类
 - restj-interface:此模块包含接口定义
 - restj-model:此模块包含数据库实体定义
 - restj-server:此模块包含api定义和服务发布
 - restj-service:此模块包含接口实现
 
 ### 2.项目运行环境
 - jdk 1.8
 - maven 3.3.9
 - mysql 5.7
 
 ### 3.编译
 
 本地开发 `mvn clean -U -Dmaven.test.skip=true package`
 
 正式环境 `mvn clean -U -Dmaven.test.skip=true -Pproduct package`
 
 ### 4.运行
 
 ./restj-server/target/release-restj-server/restj-server/bin/restart.sh