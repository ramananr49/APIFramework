package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefination sd = new StepDefination();
		
		if (StepDefination.PlaceID==null)
		{
			sd.add_place_payload_with("Apple House", "(+91) 852 963 7412)", "Teachers Colony", "Kannada");
			sd.user_calls_with_http_request("addPlaceAPI", "Post");
			sd.verify_place_id_created_maps_to_using("Apple House", "getPlaceAPI");
		}
	}
}
