package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.BaseClass;
import resources.TestData;

public class AddPlaceStep extends BaseClass {

	RequestSpecification req;

	Response res;

	TestData testdata = new TestData();

	static String Place_ID;

	@Given("User enters Add Place Payload with {string},{string},{string},{string},{string}")
	public void user_enters_add_place_payload_with(String Name, String Phone_Number, String Address, String Website, String Language) throws IOException {

		req = given().spec(requestspecification())
				.body(testdata.addplacetestdata(Name, Phone_Number, Address, Website, Language));

	}
	@When("User calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String resources, String httpmethod) {

		APIResources apiResources = APIResources.valueOf(resources);

		String getresources = apiResources.getresources();

		if(httpmethod.equalsIgnoreCase("Post"))
			res = req.when().post(getresources);

		//.then().assertThat().spec(responsespecification()).extract().response();

		else if (httpmethod.equalsIgnoreCase("Get"))
			res = req.when().get(getresources);

		else if (httpmethod.equalsIgnoreCase("Delete"))
			res = req.when().delete(getresources);


	}
	@Then("the API call is success with Status Code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(res.getStatusCode(), 200);

	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		assertEquals(getJsonPath(res, key).toString(),value);

	}

	@Then("Verify Place ID created maps to {string} using {string} with {string} HTTP request")
	public void verify_place_id_created_maps_to_using_with_http_request(String ExpectedName, String resources , String httpmethod) throws IOException  {

		Place_ID = getJsonPath(res, "place_id");

		req = given().spec(requestspecification()).queryParam("place_id", Place_ID); 

		user_calls_with_http_request(resources, httpmethod);

		String ActualName = getJsonPath(res, "name");

		assertEquals(ActualName,ExpectedName);

	}

	@Given("User enters Delete Place Payload") 
	public void user_enters_delete_place_payload() throws IOException {

		req = given().spec(requestspecification()).body(testdata.deletepayload(Place_ID));


	}

}


















