package com.sup2is.allaboutmise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan
public class AllaboutmiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllaboutmiseApplication.class, args);
	}

}
