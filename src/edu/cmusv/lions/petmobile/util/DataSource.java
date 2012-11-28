package edu.cmusv.lions.petmobile.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Data access object.
 * 
 * @author mhennessy
 */
public class DataSource {

	private static final String SERVICE_URL = "http://petlions.herokuapp.com";

	/**
	 * Interface for PET HTTP result handlers.
	 * 
	 * @author mhennessy
	 */
	public interface JsonResultHandler {
		void onJsonResult(JSONArray jsonArray);

		void onJsonResult(JSONObject jsonObject);

		void onInternetFailure();
	}

	/**
	 * Default null-object handler.
	 */
	private JsonResultHandler jsonResultHandler = new JsonResultHandler() {
		@Override
		public void onJsonResult(JSONArray jsonArray) {
		}

		@Override
		public void onJsonResult(JSONObject jsonObject) {
		}

		@Override
		public void onInternetFailure() {
		}

	};

	/**
	 * Sets the handler for HTTP results.
	 * 
	 * @param handler
	 */
	public void setJsonResultHandler(JsonResultHandler handler) {
		this.jsonResultHandler = handler;
	}

	/**
	 * Makes a HTTP request for all projects.
	 */
	public void getProjectListAsync() {
		String url = String.format("%s/projects.json", SERVICE_URL);
		makeHttpRequest(url);
	}

	/**
	 * Makes a HTTP request for a single project.
	 * 
	 * @param projectId
	 */
	public void getProjectAsync(String projectId) {
		String url = String.format("%s/projects/%s.json", SERVICE_URL, projectId);
		makeHttpRequest(url);
	}

	/**
	 * Makes a HTTP request for all project phases.
	 * 
	 * @param projectId
	 */
	public void getProjectPhaseListAsync(String projectId) {
		String url = String.format("%s/projects/%s/project_phases.json", SERVICE_URL, projectId);
		makeHttpRequest(url);
	}

	/**
	 * Makes a HTTP request for a single project phase.
	 * 
	 * @param projectId
	 * @param projectPhaseId
	 */
	public void getProjectPhaseAsync(String projectId, String projectPhaseId) {
		String url = String.format("%s/projects/%s/project_phases/%s.json", SERVICE_URL, projectId, projectPhaseId);
		makeHttpRequest(url);
	}

	/**
	 * Makes a HTTP request for all deliverables.
	 * 
	 * @param projectId
	 * @param projectPhaseId
	 */
	public void getDelivearbleListAsync(String projectId, String projectPhaseId) {
		String url = String.format("%s/projects/%s/project_phases/%s/deliverables.json", SERVICE_URL, projectId,
				projectPhaseId);
		makeHttpRequest(url);
	}

	/**
	 * Makes a HTTP request for a single deliverable.
	 * 
	 * @param projectId
	 * @param projectPhaseId
	 * @param deliverableId
	 */
	public void getDeliverableAsync(String projectId, String projectPhaseId, String deliverableId) {
		String url = String.format("%s/projects/%s/project_phases/%s/deliverables/%s.json", SERVICE_URL, projectId,
				projectPhaseId, deliverableId);
		makeHttpRequest(url);
	}

	/**
	 * Makes an asynchronous HTTP request and calls the appropriate handler
	 * method for the result.
	 * 
	 * @param url
	 */
	protected void makeHttpRequest(String url) {
		HttpRequestAsyncTask requestTask = new HttpRequestAsyncTask() {
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				// result will be null if the user does not have an internet
				// connection
				if (result != null) {
					try {
						if (isJsonArray(result)) {
							jsonResultHandler.onJsonResult(new JSONArray(result));
						} else {
							jsonResultHandler.onJsonResult(new JSONObject(result));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					jsonResultHandler.onInternetFailure();
				}
			}
		};
		requestTask.execute(url);
	}

	/**
	 * Determines if a json string is an array.
	 * 
	 * @param json
	 * @return true if the given json string is an array of json objects
	 */
	protected boolean isJsonArray(String json) {
		return json.startsWith("[");
	}
}
