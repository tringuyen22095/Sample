package com.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.personal.config.GlobalExceptionHandler;

@SpringBootApplication
@Import(GlobalExceptionHandler.class)
@ComponentScan(basePackageClasses = {
		BackendApplication.class
})
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
