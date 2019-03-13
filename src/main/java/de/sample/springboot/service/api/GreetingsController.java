package de.sample.springboot.service.api;

import de.sample.springboot.service.handler.HelloApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingsController implements HelloApi
{
    @Override
    public ResponseEntity<String> helloUserGet(String user) {
        return ResponseEntity.ok("Hello " + user);
    }
}
