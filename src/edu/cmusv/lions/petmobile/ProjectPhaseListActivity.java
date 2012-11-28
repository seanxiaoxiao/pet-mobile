package edu.cmusv.lions.petmobile;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import edu.cmusv.lions.petmobile.domain.Project;
import edu.cmusv.lions.petmobile.domain.ProjectPhase;

/**
 * Activity for displaying a list of Project Phases.
 * 
 * @author mhennessy
 */
public class ProjectPhaseListActivity extends PetListActivity {
	
	private String mProjectId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Project Phases");
	}
	
	@Override
	protected Class<?> getConstantsClass() {
		return ProjectPhase.class;
	}
	
	@Override
	protected void requestJsonData() {
		Intent intent = getIntent();
		mProjectId = intent.getStringExtra(Project.ID);
		mDataSource.getProjectPhaseListAsync(mProjectId);
	}
	
	@Override
	protected ListAdapter getListAdapter() {
		return new SimpleAdapter(this, mList, R.layout.list_item, new String[] { ProjectPhase.NAME },
				new int[] { R.id.list_item_name });
	}
	
	@Override
	protected void onItemSelected(Map<String, String> selectedItemAttributes) {
		Intent intent = new Intent(getApplicationContext(), ProjectPhaseDetailsActivity.class);
		intent.putExtra(ProjectPhase.ID, selectedItemAttributes.get(ProjectPhase.ID));
		intent.putExtra(ProjectPhase.PROJECT_ID, mProjectId);
		startActivity(intent);
	}

}
