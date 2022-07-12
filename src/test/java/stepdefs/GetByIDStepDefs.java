package stepdefs;

import static constants.TestConstants.KEY_CODE;
import static constants.TestConstants.KEY_ID;
import static constants.TestConstants.KEY_MESSAGE;
import static constants.TestConstants.KEY_STATUS;
import static constants.TestConstants.KEY_STATUS_CODE;
import static constants.TestConstants.KEY_STATUS_LINE;
import static constants.TestConstants.KEY_TYPE;
import static constants.TestConstants.URI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import state.GlobalState;

public class GetByIDStepDefs {

	public Shared sh;

	public GetByIDStepDefs(Shared sh) {
		this.sh = sh;
	}

	@When("submit get findByID")
	public void submit_get_findByID() {
		String jsonString = GlobalState.getInstance().getResponse().asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		
		sh.response = given().when().accept(ContentType.JSON)
				.get(URI + "/" + jsonPath.get(KEY_ID));
	}

	@And("get response contains$")
	public void get_response_contains(Map<String, String> map) {
		assertEquals(sh.response.getStatusCode(), Integer.parseInt(map.get(KEY_STATUS_CODE)));
		assertEquals(sh.response.getStatusLine(), map.get(KEY_STATUS_LINE));
		
		String jsonString = sh.response.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		String actual = jsonPath.getString(KEY_STATUS);
		assertEquals(actual, map.get(KEY_STATUS));
	}
	
	@And("get response after delete contains$")
	public void get_response_after_delete_contains(Map<String, String> map) {
		assertEquals(sh.response.getStatusCode(), Integer.parseInt(map.get(KEY_STATUS_CODE)));
		assertEquals(sh.response.getStatusLine(), map.get(KEY_STATUS_LINE));
		
		String jsonString = sh.response.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		
		assertEquals((int)jsonPath.get(KEY_CODE), Integer.parseInt(map.get(KEY_CODE)));
		assertEquals(jsonPath.get(KEY_TYPE), map.get(KEY_TYPE));
		assertEquals(jsonPath.get(KEY_MESSAGE), map.get(KEY_MESSAGE));
	}
}
