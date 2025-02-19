FROM eclipse-temurin:21-jdk-alpine-3.21

WORKDIR /app
COPY / ./
#COPY /* ./

RUN ./mvnw clean install -DskipTests

COPY target/dizplai-poll-0.0.1-SNAPSHOT.jar app.jar

CMD java -jar app.jar

EXPOSE 8080