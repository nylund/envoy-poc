package com.example.servicea;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    Tracer tracer;

    @Bean
    public RestTemplate restTemplate() {
        // sleuth wil automatically add interceptors
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate template;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from service a";
    }

    @GetMapping("/call-b")
    public String callB() {

        Span span = tracer.currentSpan();

        //ResponseEntity<String> response = template.getForEntity("http://localhost:9999/hello", String.class);
        ResponseEntity<String> response = template.getForEntity("http://service-b:8080/hello", String.class);

        return "Service A called B and got response: " + response.getBody();
    }

    @GetMapping("/call-b-with-envoy")
    public String callBWithEnvoy() {

        ResponseEntity<String> response = template.getForEntity("http://localhost:9091/service-b/hello-fail", String.class);

        return "Service A called B and got response: " + response.getBody();
    }


}
