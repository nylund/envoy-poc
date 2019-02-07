Envoy proof of concept

Goals
- Deploy two service with docker-compose. Both services have a envoy side car proxy container that traffic is proxied through.
- Service A calls service B
- Both services protected with rate limiting
- Use circuit breakers, e.g. no cascading failures if B is down

Optional
- Zipkin tracing
- Metrics

