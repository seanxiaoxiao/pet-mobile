package edu.cmusv.lions.petmobile;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import edu.cmusv.lions.petmobile.domain.Project;

public class ProjectListActivity extends PetListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Projects");
	}
	
	protected Class<?> getConstantsClass() {
		return Project.class;
	}
	
	@Override
	protected void requestJsonData() {
		mDataSource.getProjectListAsync();
	}
	
	protected ListAdapter getListAdapter() {
		return new SimpleAdapter(this, mList, R.layout.list_item, new String[] { Project.NAME },
				new int[] { R.id.list_item_name });
	}
	
	protected void onItemSelected(Map<String, String> selectedItemAttributes) {
		Intent intent = new Intent(getApplicationContext(), ProjectDetailsActivity.class);
		intent.putExtra(Project.ID, selectedItemAttributes.get(Project.ID));
		startActivity(intent);
	}
	
}
