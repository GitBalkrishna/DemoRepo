package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class CapsuleValidation extends Utils {

	RequestSpecification request_spec;
	ResponseSpecification response_spec;
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("Create Capsule Payload")
	public void create_Capsule_Payload() throws IOException {
		request_spec = given().spec(requestSpecification()).body(data.createCapsulePayLoad());
	}

	@Given("Get Capsule API")
	public void get_Capsule_API() throws IOException {
		request_spec = given().spec(requestSpecification());
	}

	@When("User Calls {string} with {string} http request")
	public void user_Calls_with_http_request(String resource, String api_method) {

		APIResources resource_api = APIResources.valueOf(resource);
		String api_endpoint = resource_api.getResource();
		response_spec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

		if (api_method.equalsIgnoreCase("POST"))
			response = request_spec.when().post(api_endpoint).then().spec(response_spec).extract().response();
		else if (api_method.equalsIgnoreCase("GET"))
			response = request_spec.when().get(api_endpoint).then().spec(response_spec).extract().response();
	}

	@Then("API call got Success with Status Code {int}")
	public void api_call_got_Success_with_Status_Code(Integer exp_status_code) {
		assertEquals(response.getStatusCode(), exp_status_code.intValue());
	}

}
