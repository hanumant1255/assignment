package assignment.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import models.Response;
import models.ServiceResponse;
import models.Venue;

@Service
public class LocationServiceImpl implements LocationService{
	
	@Autowired
	RestTemplate restTemplate;

	@Value("${api.venue.foursquare}")
	   private String venueApi;
	
	@Value("${client_key.foursquare}")
	   private String clientKey;
	
	@Value("${client_secret.foursquare}")
	   private String ClientSecret;
	
	@Override
	public Response getLocaltion(String query, String category) {
	       HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity <String> entity = new HttpEntity<String>(headers);
	        ServiceResponse response= restTemplate.exchange(venueApi+"?client_id="+clientKey+"&client_secret="+ClientSecret+"&v=20190425&near="+query+"&intent=browse",
	        		HttpMethod.GET, entity, ServiceResponse.class).getBody();
	        //Filter records based on category
	        if(category!=null) {
	        	Response resp=new Response();
	            List<Venue> venuesList=response.getResponse().getVenues().stream().
	            		filter(v->v!=null && !v.getCategories().isEmpty() && v.getCategories().stream().
	            		allMatch(c -> c!=null && c.getPluralName()!=null && c.getPluralName().contains(category))).
	            		collect(Collectors.toList()); 
	            resp.setVenues(venuesList);
	            return resp;
	        }
	        return response.getResponse();
	}

}
