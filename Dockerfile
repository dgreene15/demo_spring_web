FROM eclipse-temurin:21-jdk-alpine

COPY target/webclient-0.0.1-SNAPSHOT.jar demo-app.jar

ENTRYPOINT ["java","-jar","/demo-app.jar"]