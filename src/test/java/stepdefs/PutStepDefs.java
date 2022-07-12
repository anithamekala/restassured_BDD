package stepdefs;

import static constants.TestConstants.KEY_CATEGORY;
import static constants.TestConstants.KEY_NAME;
import static constants.TestConstants.URI;
import static constants.TestConstants.KEY_STATUS_CODE;
import static constants.TestConstants.KEY_STATUS_LINE;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import state.GlobalState;

public class PutStepDefs {

	public Shared sh;

	public PutStepDefs(Shared sh) {
		this.sh = sh;
	}

	@When("submit put request$")
	public void submit_put_request(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps();
		for (int i = 0; i < list.size(); i++) {
			
			JSONObject jsonObject = null;

			try {
				String jsonString = GlobalState.getInstance().getResponse().asString();
				jsonObject = new JSONObject(jsonString);
			} catch (JSONException err) {
				System.out.println("Error: " +  err.toString());
			}
			
			jsonObject.put(KEY_NAME, list.get(i).get("petName"));
			JSONObject category = (JSONObject)jsonObject.get(KEY_CATEGORY);
			category.put(KEY_NAME, list.get(i).get("categoryName"));
			jsonObject.put(KEY_CATEGORY, category);
			
			sh.response = sh.request.body(jsonObject.toString()).when().put(URI).then().log().body().extract().response();
			
			GlobalState.getInstance().setResponse(sh.response);
		}
	}

	@Then("put response contains$")
	public void query_param_as(Map<String, String> map) {

		assertEquals(sh.response.getStatusCode(), Integer.parseInt(map.get(KEY_STATUS_CODE)));
		assertEquals(sh.response.getStatusLine(), map.get(KEY_STATUS_LINE));

		String jsonString = sh.response.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		assertEquals(jsonPath.get(KEY_NAME), map.get("petName"));
		assertEquals(jsonPath.get("category.name"), map.get("categoryName"));
	}
}
