FROM openjdk:latest
WORKDIR /app/
COPY out/artifacts/cargo_logistic_jar/cargo-logistic.jar /app/cargo-logistic.jar
#CMD ["java", "-jar", "/app/car-logistic.jar"]

ENTRYPOINT ["java", "-jar", "cargo-logistic.jar"]