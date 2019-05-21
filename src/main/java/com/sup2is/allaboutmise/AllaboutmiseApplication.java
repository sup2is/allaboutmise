package com.sup2is.allaboutmise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan
@EnableCaching
@PropertySource("classpath:/allaboutmise.properties")
public class AllaboutmiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllaboutmiseApplication.class, args);
	}

}
