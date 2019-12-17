package com.flightsearch.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.flightsearch"})
public class FlightSearchApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FlightSearchApplication.class, args);
	}

}
