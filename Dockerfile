FROM maven:3.6.3-jdk-11 as builder
MAINTAINER Felix Steinke <steinke.felix@yahoo.de>

COPY  . /root/app/
WORKDIR /root/app
RUN mvn install -DskipTests

FROM openjdk:11 as jdk
MAINTAINER Felix Steinke <steinke.felix@yahoo.com>

EXPOSE 8080
COPY --from=builder /root/app/ /home/app/
WORKDIR /home/app
ENTRYPOINT ["java","-jar", "-Xmx15m", "./target/FreshPlanner-Server-0.0.1-SNAPSHOT.jar"]
