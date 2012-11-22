package edu.cmusv.lions.petmobile.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataSource {

	private static final String SERVICE_URL = "http://petlions.herokuapp.com";

	public static JSONArray getProjectList() {
		String url = String.format("%s/projects.json", SERVICE_URL);
		return JsonParser.getJSONArrayFromUrl(url);
	}

	public static JSONObject getProject(String projectId) {
		String url = String.format("%s/projects/%s.json", SERVICE_URL, projectId);
		return JsonParser.getJSONObjectFromUrl(url);
	}

	public static JSONArray getProjectPhaseList(String projectId) {
		String url = String.format("%s/projects/%s/project_phases.json", SERVICE_URL, projectId);
		return JsonParser.getJSONArrayFromUrl(url);
	}

	public static JSONObject getProjectPhase(String projectId, String projectPhaseId) {
		String url = String.format("%s/projects/%s/project_phases/%s.json", SERVICE_URL, projectId, projectPhaseId);
		return JsonParser.getJSONObjectFromUrl(url);
	}

	public static JSONArray getDelivearbles(String projectId, String projectPhaseId) {
		String url = String.format("%s/projects/%s/project_phases/%s/deliverables.json", SERVICE_URL, projectId,
				projectPhaseId);
		return JsonParser.getJSONArrayFromUrl(url);
	}

	public static JSONObject getDeliverable(String projectId, String projectPhaseId, String deliverableId) {
		String url = String.format("%s/projects/%s/project_phases/%s/deliverables/%s.json", SERVICE_URL, projectId,
				projectPhaseId, deliverableId);
		return JsonParser.getJSONObjectFromUrl(url);
	}
}
