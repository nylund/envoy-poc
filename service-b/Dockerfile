FROM envoy-service-builder as builder

WORKDIR /build
COPY . .
RUN ./mvnw package

FROM openjdk:8-jre-alpine
COPY --from=builder /build/target/service-b-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
CMD java -jar /app.jar