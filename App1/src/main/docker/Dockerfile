FROM openjdk:17-jdk-alpine
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} App1-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "App1-0.0.1-SNAPSHOT.jar"]