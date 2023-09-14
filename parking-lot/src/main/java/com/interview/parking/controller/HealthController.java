package com.interview.parking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping(path = "/isAlive")
    public ResponseEntity isAlive() {
        return ResponseEntity.noContent().build();
    }
}
