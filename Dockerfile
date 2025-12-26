FROM ubuntu:22.04
ENV TOMCAT_HOME=/u01/middleware/apache-tomcat-11.0.14
ENV PATH=$PATH:$TOMCAT_HOME/bin

RUN apt update -y
RUN apt install -y openjdk-17-jdk

RUN mkdir -p /u01/middleware
WORKDIR /u01/middleware

ADD https://dlcdn.apache.org/tomcat/tomcat-11/v11.0.14/bin/apache-tomcat-11.0.14.tar.gz .
RUN tar -xzvf apache-tomcat-11.0.14.tar.gz
RUN rm apache-tomcat-11.0.14.tar.gz

COPY target/speed2.war apache-tomcat-11.0.14/webapps
COPY run.sh apache-tomcat-11.0.14/bin
RUN chmod u+x apache-tomcat-11.0.14/bin/run.sh

ENTRYPOINT [ "apache-tomcat-11.0.14/bin/run.sh" ]