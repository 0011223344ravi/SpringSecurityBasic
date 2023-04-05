package com.example.springsecuritybasic.controlller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BasicController {


     @GetMapping("/public")
     ResponseEntity<String> getPublic(){
          return ResponseEntity.ok("This is a public accessable URL");
     }
    @GetMapping("/user")
    ResponseEntity<String> getUser(){
        return ResponseEntity.ok("This is a user accessable URL");
    }
    @GetMapping("/admin")
    ResponseEntity<String> getAdmin(){
        return ResponseEntity.ok("This is a Admin accessable URL");
    }
}
