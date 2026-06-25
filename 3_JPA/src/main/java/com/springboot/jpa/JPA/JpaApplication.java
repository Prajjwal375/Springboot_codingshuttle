package com.springboot.jpa.JPA;

import com.springboot.jpa.JPA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication {



	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

}
