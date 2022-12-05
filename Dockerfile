FROM maven:3.6.3-jdk-11 AS build
WORKDIR /build
COPY src ./src
COPY pom.xml ./
RUN mvn clean package

FROM openjdk:18-jdk
WORKDIR /app
COPY --from=build /build/target/datascraper*jar datascraper.jar
EXPOSE 8080
CMD ["java", "-jar", "datascraper.jar"]
