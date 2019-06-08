package assignment.delegates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assignment.services.LocationService;
import models.Response;

@Component
public class LocationDelegateImpl implements LocationDelegate{
	@Autowired
	LocationService locationService;
	
	@Override
	public Response getLocaltion(String query, String category) {
		return this.locationService.getLocaltion(query, category);
	}

}
