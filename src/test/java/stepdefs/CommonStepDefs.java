package stepdefs;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonStepDefs {

	public Shared sh;
	
	public CommonStepDefs(Shared sh) {
		this.sh = sh;
	}

	@Then("^the status code is (\\d+)$")
	public void theStatusCodeIs(int statusCode) {
		sh.response.then().statusCode(statusCode);
	}
	
	@Given("headers$")
	public void headers(Map<String, String> headers) {
		sh.request = given().when().contentType(headers.get("Content-Type")).accept(headers.get("accept"));
	}
}
