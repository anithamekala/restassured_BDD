package stepdefs;

import static constants.TestConstants.KEY_STATUS;
import static constants.TestConstants.URI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class GetFindByStatusStepDefs {

	public Shared sh;
	private String valueStatus;

	public GetFindByStatusStepDefs(Shared sh) {
		this.sh = sh;
	}

	@Given("^query param as \\\"(.*)\\\"$")
	public void query_param_as(String status) {
		valueStatus = status;
	}

	@When("^get findByStatus$")
	public void getAllStatuses() {
		sh.response = given().queryParam(KEY_STATUS, valueStatus).when().accept(ContentType.JSON)
				.get(URI + "/findByStatus");
	}

	@And("^response contains \\\"(.*)\\\", \\\"(.*)\\\" and \\\"(.*)\\\"$") 
	public void response_contains(String statusCode, String statusLine, String status) {

		assertEquals(sh.response.getStatusCode(), Integer.parseInt(statusCode));
		assertEquals(sh.response.getStatusLine(), statusLine);

		String jsonString = sh.response.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		List<String> actualStatuses = jsonPath.getList(KEY_STATUS);
		for (String actual : actualStatuses) {
			assertEquals(actual, status);
		}
	}
}
