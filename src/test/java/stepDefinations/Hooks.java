package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@UpdatePlace or @DeletePlace")
	public void beforeScenario() throws IOException {
		
		placeValidations pd = new placeValidations();
		if(placeValidations.place_id == null) {
			
		pd.add_place_payload_with("John", "Spanish", "Sector 36");
		pd.user_calls_with_http_request("AddPlaceAPI", "POST");
		pd.verify_place_id_created_maps_to_using("John", "GetPlaceAPI");
		}
	}

}
