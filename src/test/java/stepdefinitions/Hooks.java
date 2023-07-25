package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace") 

	public void beforescenariosingletestrun() throws IOException {

		AddPlaceStep step = new AddPlaceStep();

		if (AddPlaceStep.Place_ID==null) {

			step.user_enters_add_place_payload_with("Hobart", "12345678",
					"Snowwalk 69,ENG", "http://google.com", "English,ENG");

			step.user_calls_with_http_request("AddPlaceAPI", "POST");

			step.verify_place_id_created_maps_to_using_with_http_request("Hobart", "GetPlaceAPI", "GET");

		}

	}

}
