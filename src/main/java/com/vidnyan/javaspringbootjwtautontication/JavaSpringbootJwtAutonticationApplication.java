package com.vidnyan.javaspringbootjwtautontication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class JavaSpringbootJwtAutonticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringbootJwtAutonticationApplication.class, args);
		System.out.println("Hello Vikas");
	}

}
