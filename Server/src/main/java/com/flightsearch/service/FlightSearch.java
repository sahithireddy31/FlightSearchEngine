package com.flightsearch.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.flightsearch.model.FlightData;
import com.flightsearch.model.FlightSearchParams;

public interface FlightSearch {
	public List<FlightData> GetFlights(FlightSearchParams parameters) throws JsonParseException, JsonMappingException, IOException, URISyntaxException;
	public List<FlightData> GetAllFlights() throws JsonParseException, JsonMappingException, IOException, URISyntaxException;
}
