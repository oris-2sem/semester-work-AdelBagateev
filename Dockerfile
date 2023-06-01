FROM openjdk:17-alpine
COPY /target/burger-store-0.0.1-SNAPSHOT.jar /usr/local/lib/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/application.jar"]