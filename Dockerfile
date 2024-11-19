FROM gradle:7.6.0-jdk17 AS build

CMD ["./gradlew", "clean", "build"]

ARG JAR_FILE=build/libs/*.jar
ARG PROFILES
ARG ENV
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILES}", "-Dspring.profiles.env=${PROFILES}","-Dserver.env=${ENV}", "-jar", "/app.jar"]