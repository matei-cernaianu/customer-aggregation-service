FROM openjdk:8-jre
MAINTAINER Matei Cernaianu <matei.cernaianu@gmail.com>

ENV EXTERNAL_SERVICE_HOST host.docker.internal

ADD target/customeraccountaggregation-0.0.1-SNAPSHOT.jar /customeraccountaggregation.jar