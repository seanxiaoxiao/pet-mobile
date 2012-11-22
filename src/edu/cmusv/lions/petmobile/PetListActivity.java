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
import edu.cmusv.lions.petmobile.util.ObjectUtils;

public abstract class PetListActivity extends PetActivity {

	protected ArrayList<HashMap<String, String>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pet_list);
		initData();
		initDisplay();
	}

	private void initData() {
		list = new ArrayList<HashMap<String, String>>();
		if (isNetworkConnected()) {
			try {
				JSONArray jsonList = getJsonData();
				for (int i = 0; i < jsonList.length(); i++) {
					JSONObject jsonProject = jsonList.getJSONObject(i);
					List<String> keys = ObjectUtils.getStringConstants(getConstantsClass());
					HashMap<String, String> projectAttributes = new HashMap<String, String>();
					for (String key : keys) {
						projectAttributes.put(key, jsonProject.getString(key));
					}
					list.add(projectAttributes);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			showMessageDialog("No Internet", "You do not have an internet connection...");
		}
	}

	private void initDisplay() {
		ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(getListAdapter());
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onItemSelected(list.get(position));
			}
		});
	}

	protected abstract Class<?> getConstantsClass();

	protected abstract JSONArray getJsonData();

	protected abstract ListAdapter getListAdapter();

	protected abstract void onItemSelected(Map<String, String> selectedItemAttributes);
}
