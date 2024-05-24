FROM openjdk:17-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/Business-0.0.1-SNAPSHOT.jar Business.jar
ENTRYPOINT ["java", "-jar", "Business.jar"]
