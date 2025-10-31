package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class placeValidations extends Utils {
	RequestSpecification rs;
	ResponseSpecification resspec;
	Response response;
	public static String place_id;

	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		rs = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		// constructor will be called with value of resource which you pass
		APIResources apiResource = APIResources.valueOf(resource);
		System.out.println(apiResource.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("post")) {
			response = rs.when().post(apiResource.getResource());
		} else if (method.equalsIgnoreCase("get")) {
			response = rs.when().get(apiResource.getResource());

		} 
	}

	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);
	}

	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		// requestSpec
		place_id = getJsonPath(response, "place_id");
		rs = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");

		String actualName = getJsonPath(response, "name");

		Assert.assertEquals(actualName, expectedName);

	}
	
	@Given("Delete Place payload")
	public void delete_place_payload() throws IOException {
	   
		rs = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
	


}
