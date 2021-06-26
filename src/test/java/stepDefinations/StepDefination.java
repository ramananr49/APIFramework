package stepDefinations;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResourses;
import resources.TestDataBuilder;
import resources.Utils;

public class StepDefination extends Utils {

	RequestSpecification reqSpec;
	Response response;
	ResponseSpecification resSpec;
	TestDataBuilder data = new TestDataBuilder();
	static String PlaceID;
	
	@Given("Add Place Payload with {string} {string} {string} {string}")
	public void add_place_payload_with(String name, String phone_number, String address, String language) throws IOException
	{
		reqSpec = given().spec(requestSpecification("BaseURI")).body(data.addPlacePayload(name, phone_number, address, language));		
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		
		APIResourses resourceValue = APIResourses.valueOf(resource);
		System.out.println(resourceValue.getResource());
		
		
		if (httpMethod.equalsIgnoreCase("POST")){
			response = reqSpec.when().post(resourceValue.getResource());
		}
		else if (httpMethod.equalsIgnoreCase("GET")) {
			response = reqSpec.when().get(resourceValue.getResource());
		}
		else if (httpMethod.equalsIgnoreCase("DELETE")) {
			response = reqSpec.when().delete(resourceValue.getResource());
		}
		else if ((httpMethod.equalsIgnoreCase("UPDATE"))) {
			response = reqSpec.when().put(resourceValue.getResource());
		}
	 
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {	    
		assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expValue) {	    
		assertEquals(getJsonPath(response, keyValue, expValue), expValue);
	}
	
	@Then("Verify Place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    //get the place ID from add_place_API response to send to the get_place_API
		PlaceID = getJsonPath(response, "place_id", "");
		//create request specification for get_place_api
		reqSpec = given().spec(requestSpecification("BaseURI")).queryParam("place_id", PlaceID);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name", "");
		assertEquals(actualName, expectedName);
		System.out.println(actualName);
		System.out.println(expectedName);
	}
	
	@Given("Delete Place payload")
	public void Delete_Place_payload() throws IOException {
		
		reqSpec = given().spec(requestSpecification("BaseURI")).body(data.deletePlacePayload(PlaceID));
		
	}

}
