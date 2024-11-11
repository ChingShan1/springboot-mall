package com.chingshan.springbootmail.controller;

import com.chingshan.springbootmail.service.TokenBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RateLimiterController{

    @Autowired
    private TokenBucketService tokenBucketService;

    @GetMapping("/limited-endpoint")
    public String limitedEndpoint() {
        tokenBucketService.initializeBucket();

        if (tokenBucketService.consumeToken()) {
            return "Request successful!";
        } else {
            return "Too many requests. Please try again later.";
        }
    }


}
