build:
	./mvnw package
	cd service-a && docker build -t envoy-service-a .
	cd service-b && docker build -t envoy-service-b .
