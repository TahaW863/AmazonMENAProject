FROM openjdk:17-alpine
ADD ./target/kafka-producer-truckposition-service-1.0-SNAPSHOT.jar /app/kafka-producer-truckposition-service.jar
ENTRYPOINT ["java", "-jar", "/app/kafka-producer-truckposition-service.jar"]