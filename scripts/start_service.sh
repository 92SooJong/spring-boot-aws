#!/bin/sh

echo "> 구동"

nohup java -jar build/libs/spring-boot-aws-1.0-SNAPSHOT.jar > nohup.out 2>&1