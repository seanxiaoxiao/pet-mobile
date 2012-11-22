package edu.cmusv.lions.petmobile;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cmusv.lions.petmobile.domain.Project;
import edu.cmusv.lions.petmobile.util.DataSource;
import edu.cmusv.lions.petmobile.util.DataSource.JsonResultHandler;
import edu.cmusv.lions.petmobile.util.ObjectUtils;

public class ProjectDetailsActivity extends PetDetailsActivity {

	private DataSource mDataSource;
	private ViewGroup mDetailsContainer;
	private String mProjectId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_details);
		setTitle("Project Details");

		mDataSource = new DataSource();
		mDetailsContainer = (ViewGroup) findViewById(R.id.details_container);
		Intent intent = getIntent();
		mProjectId = intent.getStringExtra(Project.ID);

		renderDetails();
		Button phasesBtn = (Button) findViewById(R.id.phases_btn);
		phasesBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), ProjectDetailsActivity.class);
				intent.putExtra(Project.ID, mProjectId);
				startActivity(intent);
			}
		});
	}

	/*
	 * This method will need to change. Right now it is dynamic, but this will
	 * not give us what we want. We will need to hard-code what attributes we
	 * expect and in what order. We do not want to display created_at and
	 * updated_at attributes. We will also have to map ugly ID attributes to
	 * their actual objects. For example, we need to map a lifecycle_id=5 to
	 * Lifecycle='Waterfall'.
	 */
	protected void renderDetails() {
		mDataSource.setJsonResultHandler(new JsonResultHandler() {
			@Override
			public void onJsonResult(JSONObject jsonObject) {
				List<String> keys = ObjectUtils.getStringConstants(Project.class);
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
		mDataSource.getProjectAsync(mProjectId);
	}

	@Override
	protected ViewGroup getDetailsContainer() {
		return mDetailsContainer;
	}

}
