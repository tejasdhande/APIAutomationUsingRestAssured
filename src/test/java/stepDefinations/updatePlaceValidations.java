package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.Assert;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class updatePlaceValidations extends Utils {
		RequestSpecification req;
		ResponseSpecification res;
		Response response;
		TestDataBuild ts = new TestDataBuild();
		placeValidations pd = new placeValidations();
		
	@Given("Update place payload with {string}.")
	public void update_book_payload_with(String address) throws IOException {
		
		 req =  given().spec(requestSpecification()).body(ts.updateBookPayload(address));
	}

	@When("Users call {string} with {string} request.")
	public void users_call_with_request(String resource, String method) {
	    
		APIResources apiResource = APIResources.valueOf(resource);
		System.out.println(apiResource.getResource());
		
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if(method.equalsIgnoreCase("PUT")) {
			response = req.when().put(apiResource.getResource());
		}
	}

	@Then("API call should success with {int}")
	public void api_call_should_success_with(Integer statuscode) {
		Assert.assertEquals(response.getStatusCode(), statuscode);

	}

	@Then("Verify Address is updated using {string} call")
	public void verify_address_is_updated_using_call(String address) throws IOException {
		
		   String message = getJsonPath(response, "msg");
		    System.out.println(message);

		    response = given().spec(requestSpecification())
		                      .queryParam("place_id", placeValidations.place_id)
		                      .when().get("maps/api/place/get/json");

		    String actualAddress = getJsonPath(response, "address");
		    Assert.assertEquals(actualAddress, address);
		
		

	}


}
