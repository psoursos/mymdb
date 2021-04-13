#FROM ubuntu:latest
FROM ubuntu-jdk
MAINTAINER PS "petros"
EXPOSE 8086
#WORKDIR C:\Users\petros\Documents\workspace-spring-tool-suite-4-4.9.0.RELEASE\myMdb
ADD target/myMdb-app.jar .
#ENV version=docker
#RUN apt-get update && apt-get install -y openjdk-11-jdk
ENTRYPOINT ["java","-jar","myMdb-app.jar"]