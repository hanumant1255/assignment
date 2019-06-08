package assignment.delegates;

import org.springframework.stereotype.Component;

import models.Response;

@Component
public interface LocationDelegate {
	public Response getLocaltion(String query,String category);

}
