FROM openjdk:17-slim
VOLUME /tmp
COPY ./apiEncryptionKey.jks apiEncryptionKey.jks
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app.jar"]