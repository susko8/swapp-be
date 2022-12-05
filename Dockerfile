FROM maven:3.8-openjdk-18 as builder

# create app folder for sources
RUN mkdir -p /build
RUN mkdir -p /build/logs

WORKDIR /build
COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn dependency:resolve && mvn compile