FROM maven:3-jdk-11-slim as maven_toolchain
MAINTAINER iLyK Necromancer <ilyk@ilyk.im>

COPY pom.xml /tmp
COPY src /tmp/src

WORKDIR /tmp
RUN mvn clean test package

FROM openjdk:11-jdk-slim as build

WORKDIR /dds

COPY --from=maven_toolchain /tmp/target/dds*.jar /dds/dds.jar
COPY stub/prepare_for_docker.sh /usr/local/bin/prepare_for_docker
RUN prepare_for_docker /dds/dds.jar

FROM openjdk:11-jre-slim

COPY --from=build /dds/docker-dist/lib /dds/lib
COPY --from=build /dds/docker-dist/io /dds/io

ENTRYPOINT ["java", "-cp", "/dds:/dds/lib/*", "io.realm9.prometheus.dds.Stub"]
