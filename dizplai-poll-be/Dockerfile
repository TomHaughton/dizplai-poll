FROM eclipse-temurin:21-jdk-alpine-3.21

WORKDIR /app

COPY / ./

RUN ./mvnw clean install -DskipTests

CMD java -jar /app/target/dizplai-poll-0.0.1-SNAPSHOT.jar

EXPOSE 8080