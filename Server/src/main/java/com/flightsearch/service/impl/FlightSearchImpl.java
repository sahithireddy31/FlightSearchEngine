package com.flightsearch.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightsearch.model.FlightData;
import com.flightsearch.model.FlightSearchParams;
import com.flightsearch.service.FlightSearch;
@Service
public class FlightSearchImpl implements FlightSearch {
	
	private final ObjectMapper mapper = new ObjectMapper();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	@Override
	public List<FlightData> GetFlights(FlightSearchParams parameters) throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
	    // (Flight Number || (Origin && Destination)) && Date
		return  GetAllFlights().stream().filter(x -> 
	     	(x.getFlightNumber().equalsIgnoreCase(parameters.getFlightNumber()) ||
	     	(x.getOrigin().equalsIgnoreCase(parameters.getOrigin()) && x.getDestination().equalsIgnoreCase(parameters.getDestination()))) &&
	     	LocalDateTime.parse(x.getDeparture(), formatter).toLocalDate().compareTo(LocalDateTime.parse(parameters.getDate(), formatter).toLocalDate()) == 0
	     ).collect(Collectors.toList());
	}
	
	public List<FlightData> GetAllFlights() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		  List<FlightData> flights = mapper.readValue(ReadFile(), new TypeReference<List<FlightData>>(){});
		  return flights;
	}
	
	private byte [] ReadFile() throws IOException, URISyntaxException {
		Path path = Paths.get(getClass().getClassLoader().getResource("flights.json").toURI());
	    return Files.readAllBytes(path);
	}

}
