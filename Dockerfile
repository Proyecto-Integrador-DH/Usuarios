FROM openjdk:17-jdk-alpine
WORKDIR /usr/src/app
COPY target/usuarios-0.0.1-SNAPSHOT.jar /usr/src/app/app.jar
COPY pom.xml /usr/src/app/pom.xml
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

