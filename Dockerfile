FROM openjdk:8-jdk-alpine
EXPOSE 7777
ARG JAR_FILE=demoredis-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} demoredis-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","demoredis-0.0.1-SNAPSHOT.jar"]