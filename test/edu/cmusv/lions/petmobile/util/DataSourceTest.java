package edu.cmusv.lions.petmobile.util;


import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import android.test.AndroidTestCase;

public class DataSourceTest extends AndroidTestCase {

	private static final String PROJECT_LIST_JSON = "[{\"created_at\":\"2012-11-19T11:07:44-08:00\",\"description\":\"Autem dolor facere. Et impedit quisquam nemo quia ut. Sit esse laborum expedita. Vero repellat itaque. Sit tenetur totam nobis quod voluptatem minus quo.\",\"id\":1,\"lifecycle_id\":4,\"name\":\"De-engineered homogeneous local area network\",\"updated_at\":\"2012-11-19T11:07:44-08:00\"},{\"created_at\":\"2012-11-19T11:07:45-08:00\",\"description\":\"Aut numquam tempore ipsa. Dolores rerum id. Quis mollitia non.\",\"id\":2,\"lifecycle_id\":5,\"name\":\"Persevering 5th generation hierarchy\",\"updated_at\":\"2012-11-19T11:07:45-08:00\"},{\"created_at\":\"2012-11-19T11:07:45-08:00\",\"description\":\"Ea nihil eum eum nam repellendus similique. Est autem eos ab vero. Corrupti in enim ad consequuntur quisquam voluptatibus architecto. Architecto voluptatem molestiae consequatur distinctio repudiandae. Omnis tempore voluptas aut et.\",\"id\":3,\"lifecycle_id\":5,\"name\":\"Open-source optimizing initiative\",\"updated_at\":\"2012-11-19T11:07:45-08:00\"},{\"created_at\":\"2012-11-19T11:07:45-08:00\",\"description\":\"Rerum non delectus neque quasi. Ab quia et autem. Suscipit maiores id tempore impedit assumenda quis in. Aut accusantium distinctio aut.\",\"id\":4,\"lifecycle_id\":1,\"name\":\"Robust cohesive website\",\"updated_at\":\"2012-11-19T11:07:45-08:00\"},{\"created_at\":\"2012-11-19T11:07:45-08:00\",\"description\":\"Explicabo suscipit ipsum. Sit libero non eaque et eos hic voluptatem. Eius cumque rem. Facilis consectetur ipsa quis saepe mollitia accusantium laudantium.\",\"id\":5,\"lifecycle_id\":4,\"name\":\"Re-contextualized non-volatile capability\",\"updated_at\":\"2012-11-19T11:07:45-08:00\"}]";
	
	public void testGetProjectList() throws JSONException {
		JSONArray jsonObject = new JSONArray(PROJECT_LIST_JSON);
		assertTrue(jsonObject.length() > 0);
	}
	
	public void testGetProject() {
		
	}
	
	public void testGetProjectPhases() {
		
	}
	
	@Test
	public void testGetProjectPhase() {
		
	}
	
	@Test
	public void testGetDeliverables() {
		
	}
	
	@Test
	public void testGetDeliverable() {
		
	}

}
