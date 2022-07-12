package util;

import static constants.TestConstants.KEY_CATEGORY;
import static constants.TestConstants.KEY_ID;
import static constants.TestConstants.KEY_NAME;
import static constants.TestConstants.KEY_PHOTOURLS;
import static constants.TestConstants.KEY_STATUS;
import static constants.TestConstants.KEY_TAGS;
import static constants.TestConstants.VALUE_STRING;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

public class TestUtil {

	public static JSONObject getJsonObject(Map<String, String> map) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(KEY_ID, map.get("petId"));
		jsonObject.put(KEY_NAME, map.get("petName"));
		
		JSONObject category = new JSONObject();
		category.put(KEY_ID, map.get("categoryId"));
		category.put(KEY_NAME, map.get("categoryName"));
		jsonObject.put(KEY_CATEGORY, category);
		
		ArrayList<String> photoUrls = new ArrayList<>();
		photoUrls.add(VALUE_STRING);
		jsonObject.put(KEY_PHOTOURLS, photoUrls);
		
		JSONObject tag = new JSONObject();
		tag.put(KEY_ID, map.get("tagId"));
		tag.put(KEY_NAME, map.get("tagName"));
		ArrayList<JSONObject> tags = new ArrayList<>();
		tags.add(tag);
		jsonObject.put(KEY_TAGS, tags);
		
		jsonObject.put(KEY_STATUS, map.get("status"));
		
		return jsonObject;
	}
}
