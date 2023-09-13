package com.example.msstudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@SpringBootApplication
public class MsStudentApplication {

	public static void main(String[] args) {

		SpringApplication.run(MsStudentApplication.class, args);
	}

}
