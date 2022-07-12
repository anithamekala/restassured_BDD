package stepdefs;

import static constants.TestConstants.KEY_STATUS;
import static constants.TestConstants.KEY_STATUS_CODE;
import static constants.TestConstants.KEY_STATUS_LINE;
import static constants.TestConstants.URI;
import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.json.JSONObject;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import state.GlobalState;
import util.TestUtil;


public class PostStepDefs {
	
	public Shared sh;

	public PostStepDefs(Shared sh) {
		this.sh = sh;
	}
	
    @When("submit post request$")
	public void submit_post_request(Map<String, String> map) {
    	
    	JSONObject jsonObject = TestUtil.getJsonObject(map);
    	sh.response = sh.request.body(jsonObject.toString()).when()
				   .post(URI)
				   .then().log().body().extract().response();
    	
    	GlobalState.getInstance().setResponse(sh.response);
	}

    @Then("response contains$")
	public void query_param_as(Map<String, String> map) {
    	assertEquals(sh.response.getStatusCode(), Integer.parseInt(map.get(KEY_STATUS_CODE)));
		assertEquals(sh.response.getStatusLine(), map.get(KEY_STATUS_LINE));

		String jsonString = sh.response.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		assertEquals(jsonPath.get(KEY_STATUS), map.get("status"));
	}
}
