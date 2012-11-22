package edu.cmusv.lions.petmobile.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Utility class for parsing JSON.
 * 
 * @source <a
 *         href="http://www.androidhive.info/2012/01/android-json-parsing-tutorial/"
 *         >android-json-parsing-tutorial</a>
 */
public class JsonParser {

	public static JSONObject getJSONObjectFromUrl(String url) {
		JSONObject jsonObject = null;
		try {
			String jsonString = HttpUtils.makeHttpGetRequest(url);
			jsonObject = new JSONObject(jsonString);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		return jsonObject;
	}
	
	public static JSONArray getJSONArrayFromUrl(String url) {
		JSONArray jsonArray = null;
		try {
			String jsonString = HttpUtils.makeHttpGetRequest(url);
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		return jsonArray;
	}
}