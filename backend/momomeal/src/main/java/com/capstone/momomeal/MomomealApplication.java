package com.capstone.momomeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;



@SpringBootApplication
public class MomomealApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomomealApplication.class, args);
	}

}
