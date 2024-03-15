FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar
COPY src/main/resources/docker.properties /config/docker.properties
ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=file:/config/docker.properties"]

