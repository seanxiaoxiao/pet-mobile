package edu.cmusv.lions.petmobile;

import java.util.Map;

import edu.cmusv.lions.petmobile.domain.Deliverable;
import edu.cmusv.lions.petmobile.domain.ProjectPhase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class DeliverableListActivity extends PetListActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Deliverables");
	}

	@Override
	protected Class<?> getConstantsClass() {
		return Deliverable.class;
	}

	@Override
	protected void requestJsonData() {
		Intent intent = getIntent();
		mDataSource.getDelivearblesAsync(intent.getStringExtra(ProjectPhase.PROJECT_ID), intent.getStringExtra(ProjectPhase.ID));
	}

	@Override
	protected ListAdapter getListAdapter() {
		return new SimpleAdapter(this, mList, R.layout.list_item, new String[] { Deliverable.NAME },
				new int[] { R.id.list_item_name });
	}

	@Override
	protected void onItemSelected(Map<String, String> selectedItemAttributes) {
		Intent lastIntent = getIntent();
		Intent intent = new Intent(getApplicationContext(), DeliverableDetailsActivity.class);
		intent.putExtra(ProjectPhase.PROJECT_ID, lastIntent.getStringExtra(ProjectPhase.PROJECT_ID));
		intent.putExtra(Deliverable.PROJECT_PHASE_ID, lastIntent.getStringExtra(ProjectPhase.ID));
		intent.putExtra(Deliverable.ID, selectedItemAttributes.get(Deliverable.ID));
		startActivity(intent);
	}

}
