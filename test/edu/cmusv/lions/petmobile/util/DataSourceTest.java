package edu.cmusv.lions.petmobile.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;
import edu.cmusv.lions.petmobile.domain.Deliverable;
import edu.cmusv.lions.petmobile.domain.Project;
import edu.cmusv.lions.petmobile.domain.ProjectPhase;

public class DataSourceTest extends AndroidTestCase {

	private static final String PROJECT_LIST_JSON = "[{\"created_at\":\"2012-11-19T11:07:44-08:00\",\"description\":\"Autem dolor facere. Et impedit quisquam nemo quia ut. Sit esse laborum expedita. Vero repellat itaque. Sit tenetur totam nobis quod voluptatem minus quo.\",\"id\":1,\"lifecycle_id\":4,\"name\":\"De-engineered homogeneous local area network\",\"updated_at\":\"2012-11-19T11:07:44-08:00\"},{\"created_at\":\"2012-11-19T11:07:45-08:00\",\"description\":\"Aut numquam tempore ipsa. Dolores rerum id. Quis mollitia non.\",\"id\":2,\"lifecycle_id\":5,\"name\":\"Persevering 5th generation hierarchy\",\"updated_at\":\"2012-11-19T11:07:45-08:00\"},{\"created_at\":\"2012-11-19T11:07:45-08:00\",\"description\":\"Ea nihil eum eum nam repellendus similique. Est autem eos ab vero. Corrupti in enim ad consequuntur quisquam voluptatibus architecto. Architecto voluptatem molestiae consequatur distinctio repudiandae. Omnis tempore voluptas aut et.\",\"id\":3,\"lifecycle_id\":5,\"name\":\"Open-source optimizing initiative\",\"updated_at\":\"2012-11-19T11:07:45-08:00\"},{\"created_at\":\"2012-11-19T11:07:45-08:00\",\"description\":\"Rerum non delectus neque quasi. Ab quia et autem. Suscipit maiores id tempore impedit assumenda quis in. Aut accusantium distinctio aut.\",\"id\":4,\"lifecycle_id\":1,\"name\":\"Robust cohesive website\",\"updated_at\":\"2012-11-19T11:07:45-08:00\"},{\"created_at\":\"2012-11-19T11:07:45-08:00\",\"description\":\"Explicabo suscipit ipsum. Sit libero non eaque et eos hic voluptatem. Eius cumque rem. Facilis consectetur ipsa quis saepe mollitia accusantium laudantium.\",\"id\":5,\"lifecycle_id\":4,\"name\":\"Re-contextualized non-volatile capability\",\"updated_at\":\"2012-11-19T11:07:45-08:00\"}]";
	private static final String PROJECT_JSON = "{\"created_at\":\"2012-11-19T11:07:44-08:00\",\"description\":\"Autem dolor facere. Et impedit quisquam nemo quia ut. Sit esse laborum expedita. Vero repellat itaque. Sit tenetur totam nobis quod voluptatem minus quo.\",\"id\":1,\"lifecycle_id\":4,\"name\":\"De-engineered homogeneous local area network\",\"updated_at\":\"2012-11-19T11:07:44-08:00\"}";
	private static final String PROJECT_PHASE_LIST_JSON = "[{\"created_at\":\"2012-11-20T09:41:51-08:00\",\"id\":1,\"lifecycle_phase_id\":18,\"project_id\":1,\"updated_at\":\"2012-11-20T09:41:51-08:00\"},{\"created_at\":\"2012-11-20T09:41:51-08:00\",\"id\":2,\"lifecycle_phase_id\":19,\"project_id\":1,\"updated_at\":\"2012-11-20T09:41:51-08:00\"},{\"created_at\":\"2012-11-20T09:41:51-08:00\",\"id\":3,\"lifecycle_phase_id\":20,\"project_id\":1,\"updated_at\":\"2012-11-20T09:41:51-08:00\"},{\"created_at\":\"2012-11-20T09:41:51-08:00\",\"id\":4,\"lifecycle_phase_id\":21,\"project_id\":1,\"updated_at\":\"2012-11-20T09:41:51-08:00\"}]";
	private static final String PROJECT_PHASE_JSON = "{\"created_at\":\"2012-11-20T09:41:51-08:00\",\"id\":1,\"lifecycle_phase_id\":18,\"project_id\":1,\"updated_at\":\"2012-11-20T09:41:51-08:00\"}";
	private static final String DELIVERABLE_LIST_JSON = "[{\"complexity_id\":2,\"created_at\":\"2012-11-19T11:07:47-08:00\",\"deliverable_type_id\":5,\"description\":\"Quo suscipit et ut. Unde iure necessitatibus. Vitae consequatur nesciunt mollitia corporis nemo ducimus quam. A illo ex dolorum.\",\"estimated_effort\":50.0,\"estimated_production_rate\":10.0,\"estimated_size\":5.0,\"id\":1,\"name\":\"Multi-tiered context-sensitive benchmark\",\"project_phase_id\":1,\"unit_of_measure_id\":1,\"updated_at\":\"2012-11-19T11:07:47-08:00\",\"user_id\":null},{\"complexity_id\":2,\"created_at\":\"2012-11-19T11:07:47-08:00\",\"deliverable_type_id\":5,\"description\":\"Est saepe expedita atque et fugit. Odit distinctio rerum aut. Minus ut quaerat error. Unde nobis commodi aliquid ea at. In veritatis quisquam accusamus.\",\"estimated_effort\":2.0,\"estimated_production_rate\":2.0,\"estimated_size\":1.0,\"id\":2,\"name\":\"Profound static time-frame\",\"project_phase_id\":1,\"unit_of_measure_id\":1,\"updated_at\":\"2012-11-19T11:07:47-08:00\",\"user_id\":null}]";
	private static final String DELIVERABLE_JSON = "{\"complexity_id\":2,\"created_at\":\"2012-11-20T09:41:52-08:00\",\"deliverable_type_id\":4,\"description\":\"Ut et molestiae quia. Debitis culpa aspernatur distinctio laborum veritatis. Blanditiis possimus molestiae nisi et est velit inventore.\",\"estimated_effort\":200.0,\"estimated_production_rate\":20.0,\"estimated_size\":10.0,\"id\":1,\"name\":\"Up-sized leading edge hardware\",\"project_phase_id\":1,\"unit_of_measure_id\":4,\"updated_at\":\"2012-11-20T09:41:52-08:00\",\"user_id\":null}";

	public void testGetProjectList() throws JSONException {
		JSONArray jsonArray = new JSONArray(PROJECT_LIST_JSON);
		assertTrue(jsonArray.length() > 0);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			testJsonAttributes(jsonObject, Project.class);
		}
	}

	public void testGetProject() throws JSONException {
		JSONObject jsonObject = new JSONObject(PROJECT_JSON);
		testJsonAttributes(jsonObject, Project.class);
	}

	public void testGetProjectPhases() throws JSONException {
		JSONArray jsonArray = new JSONArray(PROJECT_PHASE_LIST_JSON);
		assertTrue(jsonArray.length() > 0);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			testJsonAttributes(jsonObject, ProjectPhase.class);
		}
	}

	public void testGetProjectPhase() throws JSONException {
		JSONObject jsonObject = new JSONObject(PROJECT_PHASE_JSON);
		testJsonAttributes(jsonObject, ProjectPhase.class);
	}

	public void testGetDeliverables() throws JSONException {
		JSONArray jsonArray = new JSONArray(DELIVERABLE_LIST_JSON);
		assertTrue(jsonArray.length() > 0);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			testJsonAttributes(jsonObject, Deliverable.class);
		}
	}

	public void testGetDeliverable() throws JSONException {
		JSONObject jsonObject = new JSONObject(DELIVERABLE_JSON);
		testJsonAttributes(jsonObject, Deliverable.class);
	}

	private void testJsonAttributes(JSONObject jsonObject, Class<?> constantsClass) throws JSONException {
		List<String> keys = getStringConstants(constantsClass);
		for (String key : keys) {
			jsonObject.getString(key);
		}
	}

	private List<String> getStringConstants(Class<?> constantsClass) {
		List<String> stringConstants = new ArrayList<String>();
		for (Field field : constantsClass.getFields()) {
			if (Modifier.isStatic(field.getModifiers())) {
				Object obj;
				try {
					obj = field.get(null);
					if (obj instanceof String) {
						stringConstants.add(obj.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return stringConstants;
	}

}
