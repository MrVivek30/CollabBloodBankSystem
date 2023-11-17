package com.trackobit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Blood_Bank_With_H2_SBApplication extends SpringBootServletInitializer {

	@Override

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(Blood_Bank_With_H2_SBApplication.class);

	}
	public static void main(String[] args) {
		SpringApplication.run(Blood_Bank_With_H2_SBApplication.class, args);
		
	
		
		
	}

}
