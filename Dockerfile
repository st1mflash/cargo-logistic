FROM maven:3.8.6-eclipse-temurin-17 as builder
WORKDIR /opt/app
COPY mvnw pom.xml ./
COPY ./src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]







#FROM openjdk:17-jdk-slim
#WORKDIR /app/
#COPY out/artifacts/cargo_logistic_jar/cargo-logistic.jar /app/cargo-logistic.jar
#-COPY out/artifacts/postgresql/postgresql.jar /app/postgresql.jar

#-ENV CLASSPATH /app/postgresql.jar
#-RUN rm -f /app/commons-logging.jar
#-CMD ["java", "-jar", "/app/car-logistic.jar"]

#ENTRYPOINT ["java", "-jar", "cargo-logistic.jar"]