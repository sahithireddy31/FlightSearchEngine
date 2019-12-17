package com.flightsearch.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightsearch.model.FlightData;
import com.flightsearch.model.FlightSearchParams;
import com.flightsearch.service.FlightSearch;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FlightSearchController {

	@Autowired
	private FlightSearch flightSearch;

	@PostMapping(path ="/flight-search")
	public List<FlightData> GetFlights(@RequestBody FlightSearchParams params) {
		try {
			return flightSearch.GetFlights(params);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			return new ArrayList<FlightData>();
		}

	}
	@GetMapping(path ="/flights")
	public List<FlightData> Flights() {
		try {
			return flightSearch.GetAllFlights();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			return new ArrayList<FlightData>();
		}
	}

}
