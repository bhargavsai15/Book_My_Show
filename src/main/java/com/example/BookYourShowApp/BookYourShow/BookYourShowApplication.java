package com.example.BookYourShowApp.BookYourShow;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class BookYourShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookYourShowApplication.class, args);
	}

}
