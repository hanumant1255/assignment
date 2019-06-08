package assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import assignment.delegates.LocationDelegate;
import models.Response;
import models.Venue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class LocationController {
	
	
	@Autowired
	LocationDelegate locationDelegate;
	


    @RequestMapping("/search")
        public Response getLocaltion(@RequestParam("query") String query,@RequestParam("category") Optional<String> category) {
    	String tempCategory=null;
    	
    	if(category.isPresent()) {
    		tempCategory=category.get();
    	}
         return this.locationDelegate.getLocaltion(query, tempCategory);
        }
}
