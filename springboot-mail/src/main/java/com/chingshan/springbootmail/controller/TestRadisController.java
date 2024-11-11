package com.chingshan.springbootmail.controller;


import com.chingshan.springbootmail.service.redisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testRedis-users")
public class TestRadisController {

    @Autowired
    private redisTestService redisTestService;

    @PostMapping("/{userId}")
    public String saveUser(@PathVariable String userId, @RequestBody String user) {
        redisTestService.saveUser(userId, user);
        return "User saved!";
    }

    @GetMapping("/{userId}")
    public Object getUser(@PathVariable String userId) {
        return redisTestService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        redisTestService.deleteUser(userId);
        return "User deleted!";
    }
}
