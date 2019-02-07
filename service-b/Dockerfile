FROM openjdk:8-jdk-alpine as builder

WORKDIR /build

# Maven cache
COPY pom.xml mvnw ./
COPY .mvn/ ./.mvn/
RUN ./mvnw -B dependency:resolve-plugins dependency:resolve

COPY . .
RUN ./mvnw package

FROM openjdk:8-jre-alpine
COPY --from=builder /build/target/service-b-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
CMD java -jar /app.jar