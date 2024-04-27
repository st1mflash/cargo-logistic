FROM openjdk:latest
WORKDIR /app/
COPY out/artifacts/car_logistic_jar/car-logistic.jar /app/car-logistic.jar
#CMD ["java", "-jar", "/app/car-logistic.jar"]

ENTRYPOINT ["java", "-jar", "car-logistic.jar"]