package stepdefs;

import static constants.TestConstants.KEY_STATUS;
import static constants.TestConstants.KEY_STATUS_CODE;
import static constants.TestConstants.KEY_STATUS_LINE;
import static constants.TestConstants.URI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

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

	@Given("query param as$")
	public void query_param_as(Map<String, String> map) {
		valueStatus = map.get(KEY_STATUS);
	}

	@When("^get findByStatus$")
	public void getAllStatuses() {
		sh.response = given().queryParam(KEY_STATUS, valueStatus).when().accept(ContentType.JSON)
				.get(URI + "/findByStatus");
	}

	@And("response for findByStatus contains$")
	public void response_for_findByStatus_contains(Map<String, String> map) {
		assertEquals(sh.response.getStatusCode(), Integer.parseInt(map.get(KEY_STATUS_CODE)));
		assertEquals(sh.response.getStatusLine(), map.get(KEY_STATUS_LINE));
		
		String jsonString = sh.response.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		List<String> actualStatuses = jsonPath.getList(KEY_STATUS);
		for (String actual : actualStatuses) {
			assertEquals(actual, map.get(KEY_STATUS));
		}
	}
}
