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
import resources.LibraryAPIResources;
import resources.TestDataBuilder;
import resources.Utils;

public class LibrarystepDefination extends Utils{

	RequestSpecification reqSpec;
	Response response;
	ResponseSpecification resSpec;
	TestDataBuilder data = new TestDataBuilder();
	
    @Given("AddBookAPI payload with {string}  {string} {string} {string}")
    public void add_book_api_payload_with(String name, String isbn, String aisle, String author) throws IOException {
    	
		reqSpec = given().spec(requestSpecification("LibraryAPIBaseURI"))
				.body(data.addBookPayload(name, isbn, aisle, author));
    }
    
    @Given("^GetBookByAuhtorAPI payload with \"([^\"]*)\"$")
    public void getbookbyauhtorapi_payload_with_something(String authorName) throws Throwable {
    	reqSpec = given().spec(requestSpecification("LibraryAPIBaseURI"))
    			.queryParam("AuthorName", authorName);
    }

    @Given("^GetBookByIdAPI payload with \"([^\"]*)\"$")
    public void getbookbyidapi_payload_with_something(String bookID) throws Throwable {
    	reqSpec = given().spec(requestSpecification("LibraryAPIBaseURI"))
    			.queryParam("ID", bookID);
    }

    @Given("^DeleteBookByIdAPI payload with \"([^\"]*)\"$")
    public void deletebookbyidapi_payload_with_something(String bookID) throws Throwable {
    	reqSpec = given().spec(requestSpecification("LibraryAPIBaseURI"))
    			.body(data.deleteBookPayload(bookID));
    }

    @When("user hits {string} with {string} http method")
    public void user_hits_with_http_method(String resources, String httpMethod) {
       	//create object of LibraryAPIResource java class, which has the resources details of Library API.
    	LibraryAPIResources resourceValue = LibraryAPIResources.valueOf(resources);
    	System.out.println(resourceValue.getResource());
    	
    	if (httpMethod.equalsIgnoreCase("Post"))
    	{
    		response = reqSpec.when().post(resourceValue.getResource());
    	}
    	else if (httpMethod.equalsIgnoreCase("Get")) {
    		response = reqSpec.when().get(resourceValue.getResource());
    	}
    	else if (httpMethod.equalsIgnoreCase("Delete"))
    	{
    		response = reqSpec.when().delete(resourceValue.getResource());
    	}
    }
    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer expStatusCode) { 	
     	if (expStatusCode==200)
     	{
     		assertEquals(response.getStatusCode(), 200);
     	}
     	else if (expStatusCode==404)
     	{
     		assertEquals(response.getStatusCode(), 404);
     	}
    	
    }
    @Then("{string} in Response body is {string}")
    public void in_response_body_is(String Key, String expValue) {
    	
    	String actValue = getJsonPath(response, Key, expValue);
    	assertEquals(expValue, actValue);
    	System.out.println("Expected Value is :"+expValue+" and Actual Value is :"+actValue);
    }

}
