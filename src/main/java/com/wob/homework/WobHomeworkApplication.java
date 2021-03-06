package com.wob.homework;

import com.wob.homework.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WobHomeworkApplication {

    @Autowired
    private HomeworkService homeworkService;

    public static void main(String[] args) {
        SpringApplication.run(WobHomeworkApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            homeworkService.fetchDataAndCreateReport();
        };
    }
}
