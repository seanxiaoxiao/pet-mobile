package edu.cmusv.lions.petmobile;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cmusv.lions.petmobile.util.DataSource;
import edu.cmusv.lions.petmobile.util.DataSource.JsonResultHandler;

/**
 * Activity for displaying details about a JSON object.
 * 
 * @author mhennessy
 */
public abstract class PetDetailsActivity extends PetActivity {

	protected DataSource mDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDataSource = new DataSource();
	}

	/**
	 * Populates the view with JSON key-value pairs.
	 */
	protected void renderDetails() {
		mDataSource.setJsonResultHandler(new JsonResultHandler() {
			@Override
			public void onJsonResult(JSONObject jsonObject) {
				for (String key : getDisplayKeys()) {
					try {
						String label = convertJsonKeyToLabel(key);
						String value = jsonObject.getString(key);
						addLabelValuePair(label, value);
						addSpace();
					} catch (JSONException e) {
						e.printStackTrace();
						showMessageDialog("Error:", e.getMessage());
					}
				}
			}

			@Override
			public void onJsonResult(JSONArray jsonArray) {
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
	 * Converts the given JSON key to a display ready label. </br> </br> Example
	 * Input: "estimated_production_rate" </br> Example Output:
	 * "Estimated Production Rate"
	 * 
	 * @param jsonKey
	 * @return a display ready label
	 */
	protected String convertJsonKeyToLabel(String jsonKey) {
		return WordUtils.capitalize(jsonKey.replace("_", " "));
	}

	/**
	 * Adds widgets to display the given label and value.
	 * 
	 * @param label
	 * @param value
	 */
	protected void addLabelValuePair(String label, String value) {
		bold(addText(label));
		addText(value);
	}

	/**
	 * Adds a widget to display the given string.
	 * 
	 * @param text
	 * @return textView - for method chaining purposes
	 */
	protected TextView addText(String text) {
		TextView textView = new TextView(this);
		textView.setText(text);
		getDetailsContainer().addView(textView);
		return textView;
	}

	/**
	 * Makes the text of the given widget bold.
	 * 
	 * @param textView
	 *            - for method chaining purposes
	 */
	protected void bold(TextView textView) {
		textView.setTypeface(null, Typeface.BOLD);
	}

	/**
	 * Adds space.
	 * 
	 * @return view - for method chaining purposes
	 */
	protected View addSpace() {
		return addSpace(6);
	}

	/**
	 * Adds space.
	 * 
	 * @param px
	 *            - the amount of space to add in pixels
	 * @return view - for method chaining purposes
	 */
	protected View addSpace(int px) {
		View space = new View(this);
		getDetailsContainer().addView(space, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px));
		return space;
	}

	/**
	 * Adds a divider.
	 * 
	 * @return view - for method chaining purposes.
	 */
	protected View addDivider() {
		View divider = new View(this);
		divider.setBackgroundColor(0xFF5E5E5E);
		getDetailsContainer().addView(divider, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
		return divider;
	}

	/**
	 * @return a string array of JSON keys to display.
	 */
	protected abstract String[] getDisplayKeys();

	/**
	 * @return the container widget for JSON details.
	 */
	protected abstract ViewGroup getDetailsContainer();

	/**
	 * This method should use a data source to request a JSON string.
	 */
	protected abstract void requestJsonData();

}
