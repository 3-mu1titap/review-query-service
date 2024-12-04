FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/reviewQuery-0.0.1-SNAPSHOT.jar review-query-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "review-query-service.jar"]

ENV TZ=Asia/Seoul
