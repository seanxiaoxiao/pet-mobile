package edu.cmusv.lions.petmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cmusv.lions.petmobile.domain.Project;

/**
 * Activity for displaying details about a Project.
 * 
 * @author mhennessy
 */
public class ProjectDetailsActivity extends PetDetailsActivity {

	private ViewGroup mDetailsContainer;
	private String mProjectId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Project Details");
		setContentView(R.layout.activity_project_details);

		mDetailsContainer = (ViewGroup) findViewById(R.id.details_container);

		Intent intent = getIntent();
		mProjectId = intent.getStringExtra(Project.ID);

		renderDetails();
		Button phasesBtn = (Button) findViewById(R.id.phases_btn);
		phasesBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), ProjectPhaseListActivity.class);
				intent.putExtra(Project.ID, mProjectId);
				startActivity(intent);
			}
		});
	}

	@Override
	protected String[] getDisplayKeys() {
		return new String[] { Project.NAME, Project.DESCRIPTION, Project.LIFECYCLE_NAME };
	}

	@Override
	protected ViewGroup getDetailsContainer() {
		return mDetailsContainer;
	}

	@Override
	protected void requestJsonData() {
		mDataSource.getProjectAsync(mProjectId);
	}

}
