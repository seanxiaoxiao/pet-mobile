package edu.cmusv.lions.petmobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import edu.cmusv.lions.petmobile.util.DataSource;
import edu.cmusv.lions.petmobile.util.DataSource.JsonResultHandler;
import edu.cmusv.lions.petmobile.util.ObjectUtils;

public abstract class PetListActivity extends PetActivity {

	protected DataSource mDataSource;
	protected ArrayList<HashMap<String, String>> mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pet_list);
		mDataSource = new DataSource();
		renderList();
	}

	private void renderList() {
		mList = new ArrayList<HashMap<String, String>>();
		mDataSource.setJsonResultHandler(new JsonResultHandler() {
			@Override
			public void onJsonResult(JSONArray jsonArray) {
				try {
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonProject = jsonArray.getJSONObject(i);
						List<String> keys = ObjectUtils.getStringConstants(getConstantsClass());
						HashMap<String, String> projectAttributes = new HashMap<String, String>();
						for (String key : keys) {
							String value = jsonProject.getString(key);
							projectAttributes.put(key, value);
						}
						mList.add(projectAttributes);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				ListView listView = (ListView) findViewById(R.id.list);
				listView.setAdapter(getListAdapter());
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						onItemSelected(mList.get(position));
					}
				});
			}

			@Override
			public void onJsonResult(JSONObject jsonObject) {
				// no-op
			}

			@Override
			public void onInternetFailure() {
				showMessageDialog("Oops", "You do not have an internet connection.");
			}
		});
		requestJsonData();
	}
	
	protected abstract Class<?> getConstantsClass();

	protected abstract void requestJsonData();

	protected abstract ListAdapter getListAdapter();

	protected abstract void onItemSelected(Map<String, String> selectedItemAttributes);
}
