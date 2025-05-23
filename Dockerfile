FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/webclient-0.0.1-SNAPSHOT.jar demo-app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","demo-app.jar"]