package com.eukon05.financetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinancetrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancetrackerApplication.class, args);
    }

}
