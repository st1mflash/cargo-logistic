FROM openjdk:17-jdk-slim
WORKDIR /app/
COPY out/artifacts/cargo_logistic_jar/cargo-logistic.jar /app/cargo-logistic.jar
#COPY out/artifacts/postgresql/postgresql.jar /app/postgresql.jar

#ENV CLASSPATH /app/postgresql.jar
#RUN rm -f /app/commons-logging.jar
#CMD ["java", "-jar", "/app/car-logistic.jar"]

ENTRYPOINT ["java", "-jar", "cargo-logistic.jar"]