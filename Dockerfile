FROM openjdk:8-jdk-alpine
EXPOSE 7777
ARG JAR_FILE=target/demoredis-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} target/demoredis-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","target/demoredis-0.0.1-SNAPSHOT.jar"]