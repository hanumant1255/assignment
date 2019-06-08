package assignment.services;

import org.springframework.stereotype.Service;

import models.Response;

@Service
public interface LocationService {
	public Response getLocaltion(String query,String category);
}
