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

/**
 * Base PET list activity. This class defines methods that all PET list
 * activities have in common. It also defines common tasks while allowing
 * subclasses to override steps that vary.
 * 
 * @author mhennessy
 */
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

	/**
	 * Populates the list view with content.
	 */
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

	/**
	 * @return a class that stores JSON keys as string constants.
	 */
	protected abstract Class<?> getConstantsClass();

	/**
	 * This method should use a data source to request a JSON string.
	 */
	protected abstract void requestJsonData();

	/**
	 * @return the list adapter to be used by the list view.
	 */
	protected abstract ListAdapter getListAdapter();

	/**
	 * Called when an item is selected.
	 * 
	 * @param selectedItemAttributes a jsonKey-jsonValue map for the selected JSON object.
	 */
	protected abstract void onItemSelected(Map<String, String> selectedItemAttributes);
}
