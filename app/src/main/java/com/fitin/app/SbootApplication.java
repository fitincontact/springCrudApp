package com.fitin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.fitin.*"})
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class SbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbootApplication.class, args);
        System.out.println("SbootApplication started");
    }

    @PostConstruct
    public void post() {
        System.out.println("SbootApplication PostConstruct");
    }
}
