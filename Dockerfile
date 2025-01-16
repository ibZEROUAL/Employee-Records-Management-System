FROM openjdk:17

COPY target/Employee-Records-Management-System-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]