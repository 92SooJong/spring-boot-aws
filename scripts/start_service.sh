#!/bin/sh

echo "> 구동"

JAR_NAME=spring-boot-aws-1.0-SNAPSHOT.jar
REPOSITORY=/home/ec2-user/app/step3

nohup java -jar \
    -Dspring.config.location=/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties,classpath:/application-real.properties \
    -Dspring.profiles.active=real \
    $REPOSITORY/build/libs/$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
