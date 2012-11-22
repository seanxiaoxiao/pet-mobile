package edu.cmusv.lions.petmobile;

import java.util.Map;

import org.json.JSONArray;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import edu.cmusv.lions.petmobile.domain.Project;
import edu.cmusv.lions.petmobile.util.DataSource;

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
	protected JSONArray getJsonData() {
		return DataSource.getProjectList();
	}
	
	protected ListAdapter getListAdapter() {
		return new SimpleAdapter(this, list, R.layout.list_item, new String[] { Project.NAME },
				new int[] { R.id.list_item_name });
	}
	
	protected void onItemSelected(Map<String, String> selectedItemAttributes) {
		Intent intent = new Intent(getApplicationContext(), ProjectDetailsActivity.class);
		intent.putExtra(Project.ID, selectedItemAttributes.get(Project.ID));
		startActivity(intent);
	}
	
}
