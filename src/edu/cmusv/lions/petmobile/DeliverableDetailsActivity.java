package edu.cmusv.lions.petmobile;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.cmusv.lions.petmobile.domain.Deliverable;
import edu.cmusv.lions.petmobile.domain.ProjectPhase;
import edu.cmusv.lions.petmobile.util.DataSource;
import edu.cmusv.lions.petmobile.util.ObjectUtils;
import edu.cmusv.lions.petmobile.util.DataSource.JsonResultHandler;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

public class DeliverableDetailsActivity extends PetDetailsActivity {

	private DataSource mDataSource;
	private ViewGroup mDetailsContainer;
	private String mDeliverableId;
	private String mProjectId;
	private String mProjectPhaseId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deliverable_details);
		setTitle("Deliverable Details");

		mDataSource = new DataSource();
		mDetailsContainer = (ViewGroup) findViewById(R.id.details_container);

		Intent intent = getIntent();
		mDeliverableId = intent.getStringExtra(Deliverable.ID);
		mProjectId = intent.getStringExtra(ProjectPhase.PROJECT_ID);
		mProjectPhaseId = intent.getStringExtra(Deliverable.PROJECT_PHASE_ID);
		
		renderDetails();
	}

	protected void renderDetails() {
		mDataSource.setJsonResultHandler(new JsonResultHandler() {
			@Override
			public void onJsonResult(JSONObject jsonObject) {
				List<String> keys = ObjectUtils.getStringConstants(Deliverable.class);
				for (String key : keys) {
					try {
						String value = jsonObject.getString(key);
						addLabelValuePair(key, value);
						addSpace(6);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onJsonResult(JSONArray jsonArray) {
				// no-op
			}

			@Override
			public void onInternetFailure() {
				showMessageDialog("Oops", "You do not have an internet connection.");
			}
		});
		mDataSource.getDeliverableAsync(mProjectId, mProjectPhaseId, mDeliverableId);
	}

	@Override
	protected ViewGroup getDetailsContainer() {
		return mDetailsContainer;
	}


}
