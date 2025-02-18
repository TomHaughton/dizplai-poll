package com.thomashaughton.dizplaipoll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DizplaiPollApplication {

    public static void main(String[] args) {
        SpringApplication.run(DizplaiPollApplication.class, args);
    }

}
