package stepdefs;

import static constants.TestConstants.KEY_ID;
import static constants.TestConstants.KEY_STATUS_CODE;
import static constants.TestConstants.KEY_STATUS_LINE;
import static constants.TestConstants.URI;
import static constants.TestConstants.KEY_CODE;
import static constants.TestConstants.KEY_MESSAGE;
import static constants.TestConstants.KEY_TYPE;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.Map;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import state.GlobalState;

public class DeleteByIdStepDefs {

	public Shared sh;
	private String id;

	public DeleteByIdStepDefs(Shared sh) {
		this.sh = sh;
	}

	@When("submit delete by id")
	public void submit_delete_by_id() {
		String jsonString = GlobalState.getInstance().getResponse().asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		id =  "" + jsonPath.get(KEY_ID);

		sh.response = given().when().contentType(ContentType.JSON).accept(ContentType.JSON)
				.delete(URI + "/" + id).then().log().body().extract().response();
	}

	@Then("delete response contains$")
	public void delete_response_contains(Map<String, String> map) {
		assertEquals(sh.response.getStatusCode(), Integer.parseInt(map.get(KEY_STATUS_CODE)));
		assertEquals(sh.response.getStatusLine(), map.get(KEY_STATUS_LINE));
		
		String jsonString = sh.response.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		assertEquals((int)jsonPath.get(KEY_CODE), Integer.parseInt(map.get(KEY_CODE)));
		assertEquals(jsonPath.get(KEY_TYPE), map.get(KEY_TYPE));
		assertEquals(jsonPath.get(KEY_MESSAGE), id);
	}
}
