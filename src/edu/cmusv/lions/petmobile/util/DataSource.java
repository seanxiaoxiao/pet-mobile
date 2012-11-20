package edu.cmusv.lions.petmobile.util;

import org.json.JSONObject;

public class DataSource {

	private static JSONParser jsonParser = new JSONParser();
	
	private static final String SERVICE_URL = "http://petlions.herokuapp.com";
	
	public static JSONObject getProjectList() {
		return jsonParser.getJSONFromUrl(String.format("%s/projects.json", SERVICE_URL));
	}
	
	public static JSONObject getProject(int projectId) {
		return null;
	}
	
	public static JSONObject getProjectPhaseList(int projectId) {
		return null;
	}
	
	public static JSONObject getProjectPhase(int projectPhaseId) {
		return null;
	}
	
	public static JSONObject getDelivearbles(int projectId, int projectPhaseId) {
		return null;
	}
	
	public static JSONObject getDeliverable(int projectId, int projectPhaseId, int deliverableId) {
		return null;
	}
}
