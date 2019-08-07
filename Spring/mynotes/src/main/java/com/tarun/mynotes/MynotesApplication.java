package com.tarun.mynotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MynotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MynotesApplication.class, args);
	}

}
