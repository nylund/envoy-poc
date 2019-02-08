FROM envoy-service-builder as builder

WORKDIR /build
COPY ./src src/
COPY ./pom.xml pom.xml
RUN ./mvnw package

FROM envoyproxy/envoy-alpine:latest as envoy-source

FROM openjdk:8-jre-alpine

COPY --from=envoy-source /usr/local/bin/envoy /bin/envoy
RUN apk --no-cache add ca-certificates wget && \
    wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub && \
    wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.28-r0/glibc-2.28-r0.apk && \
    apk add glibc-2.28-r0.apk

COPY ./start.sh /bin/start.sh
COPY ./envoy.yaml /etc/envoy.yaml
COPY --from=builder /build/target/service-a-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080
CMD start.sh