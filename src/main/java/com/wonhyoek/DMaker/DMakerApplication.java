package com.wonhyoek.DMaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DMakerApplication.class, args);
	}

}
