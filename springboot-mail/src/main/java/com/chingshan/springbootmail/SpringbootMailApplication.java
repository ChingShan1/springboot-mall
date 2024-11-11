package com.chingshan.springbootmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication
public class SpringbootMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMailApplication.class, args);
    }

}
