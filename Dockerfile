FROM openjdk:17-jdk-slim

RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

RUN chown appuser:appgroup app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/novel-noodle-0.0.1-SNAPSHOT.jar"]
