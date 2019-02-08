Envoy proof of concept

Goals
- Deploy two service with docker-compose. Both services have a envoy side car proxy container that traffic is proxied through.
- Service A calls service B
- Automated retries
- Both services protected with rate limiting
- Use circuit breakers, e.g. no cascading failures if B is down

Optional
- Zipkin tracing
- Metrics


### Notes

- Installing envoy on top of vanilla openjdk alpine images requires glibc,
  - https://github.com/sgerrand/alpine-pkg-glibc

- Restart service after changes:
 docker-compose up -d --build service-a

- Test envoy locally
  - docker run -it -p 9090:9090 -v $(pwd)/envoy.yaml:/etc/envoy.yaml envoyproxy/envoy-alpine:latest envoy -c /etc/envoy.yaml
