package com.example.servicea;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from service a";
    }

    @GetMapping("/call-b")
    public String callB() {

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.getForEntity("http://service-b:8080/hello", String.class);

        return "Service A called B and got response: " + response.getBody();
    }

    @GetMapping("/call-b-with-envoy")
    public String callBWithEnvoy() {

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.getForEntity("http://localhost:9090/service-b/hello", String.class);

        return "Service A called B and got response: " + response.getBody();
    }


}
