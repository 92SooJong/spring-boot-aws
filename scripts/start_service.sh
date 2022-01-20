#!/bin/sh

echo "> 구동"

nohup java -jar /home/ec2-user/app/step2/spring-boot-aws/build/libs/spring-boot-aws-1.0-SNAPSHOT.jar > nohup.out 2>&1