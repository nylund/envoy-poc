#!/bin/sh

java -jar /app.jar &
envoy -c /etc/envoy.yaml --service-cluster service-b --service-node service-b
