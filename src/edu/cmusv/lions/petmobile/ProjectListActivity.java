package edu.cmusv.lions.petmobile;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import edu.cmusv.lions.petmobile.domain.Project;

/**
 * Activity for displaying a list of Projects.
 * 
 * @author mhennessy
 */
public class ProjectListActivity extends PetListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Projects");
	}
	
	@Override
	protected Class<?> getConstantsClass() {
		return Project.class;
	}
	
	@Override
	protected void requestJsonData() {
		mDataSource.getProjectListAsync();
	}
	
	@Override
	protected ListAdapter getListAdapter() {
		return new SimpleAdapter(this, mList, R.layout.list_item, new String[] { Project.NAME },
				new int[] { R.id.list_item_name });
	}
	
	@Override
	protected void onItemSelected(Map<String, String> selectedItemAttributes) {
		Intent intent = new Intent(getApplicationContext(), ProjectDetailsActivity.class);
		intent.putExtra(Project.ID, selectedItemAttributes.get(Project.ID));
		startActivity(intent);
	}
	
}
