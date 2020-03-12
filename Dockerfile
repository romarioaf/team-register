FROM openjdk:8-jdk-alpine
MAINTAINER romariofreitas.it@gmail.com
EXPOSE 8888
COPY target/team-register-0.0.1-SNAPSHOT.jar /app.jar
CMD java -jar app.jar