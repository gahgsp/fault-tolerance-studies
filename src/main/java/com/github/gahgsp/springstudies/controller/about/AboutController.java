package com.github.gahgsp.springstudies.controller.about;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/about")
public class AboutController {

    @GetMapping
    public ResponseEntity about() {
        return ResponseEntity.ok("The service is UP and RUNNING!");
    }

}
