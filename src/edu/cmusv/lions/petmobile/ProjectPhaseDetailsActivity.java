package edu.cmusv.lions.petmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cmusv.lions.petmobile.domain.ProjectPhase;

public class ProjectPhaseDetailsActivity extends PetDetailsActivity {

	private ViewGroup mDetailsContainer;
	private String mProjectId;
	private String mProjectPhaseId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Project Phase Details");
		setContentView(R.layout.activity_project_phase_details);

		mDetailsContainer = (ViewGroup) findViewById(R.id.details_container);
		Intent intent = getIntent();
		mProjectId = intent.getStringExtra(ProjectPhase.PROJECT_ID);
		mProjectPhaseId = intent.getStringExtra(ProjectPhase.ID);

		renderDetails();
		Button deliverablesBtn = (Button) findViewById(R.id.deliverables_button);
		deliverablesBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), DeliverableListActivity.class);
				intent.putExtra(ProjectPhase.PROJECT_ID, mProjectId);
				intent.putExtra(ProjectPhase.ID, mProjectPhaseId);
				startActivity(intent);
			}
		});
	}

	@Override
	protected String[] getDisplayKeys() {
		return new String[] { ProjectPhase.NAME, ProjectPhase.PROJECT_NAME };
	}

	@Override
	protected ViewGroup getDetailsContainer() {
		return mDetailsContainer;
	}

	@Override
	protected void requestJsonData() {
		mDataSource.getProjectPhaseAsync(mProjectId, mProjectPhaseId);
	}

}
