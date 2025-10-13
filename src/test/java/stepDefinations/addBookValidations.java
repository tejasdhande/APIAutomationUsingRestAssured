package stepDefinations;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;

public class addBookValidations {
	
	RequestSpecification req;
	RequestSpecification reqs;
	JsonPath js;

	ResponseSpecification resspec;
	Response response;
	
	TestDataBuild testData = new TestDataBuild();
	
	@Given("Add Book payload with {string},{string},{string} and {string}")
	public void add_book_payload_with(String name,String isbn,String aisle,String author){
		
		req = new RequestSpecBuilder().setBaseUri("http://216.10.245.166").setContentType(ContentType.JSON).build();
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		reqs = given().spec(req).body(testData.addBookPayload(name, isbn, aisle, author));

	}

	@When("User calls  AddBookAPI with Post http request")
	public void user_calls_add_book_api_with_post_http_request() {
		
		response = reqs.when().post("/Library/Addbook.php").then().extract().response();
	}

	@Then("The API call is success with the status code {int}")
	public void the_api_call_is_success_with_the_status_code(Integer code) {
	    Assert.assertEquals(response.statusCode(), code);
		
	}

	@Then("Response body message should be {string}")
	public void response_body_message_should_be(String expectedMsg) {
		js = new JsonPath(response.asString());
		Assert.assertEquals(js.getString("Msg"), expectedMsg);
	}
	
}
