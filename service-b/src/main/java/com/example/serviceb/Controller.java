package com.example.serviceb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    int megaFail = 1;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from service b";
    }

    @GetMapping("/hello-fail")
    public ResponseEntity<String> helloFailure() {
        megaFail++;

        if (megaFail % 3 == 0) {
            return new ResponseEntity<String>("Something is wrong in b", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Hello from service b", HttpStatus.OK);
    }
}
