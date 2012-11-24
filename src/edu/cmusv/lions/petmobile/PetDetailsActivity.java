package edu.cmusv.lions.petmobile;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class PetDetailsActivity extends PetActivity {
	
	protected void addLabelValuePair(String label, String value) {
		boldLabel(addLabel(label));
		addLabel(value);
	}

	protected TextView addLabel(String text) {
		TextView textView = new TextView(this);
		textView.setText(text);
		getDetailsContainer().addView(textView);
		return textView;
	}

	protected void boldLabel(TextView textView) {
		textView.setTypeface(null, Typeface.BOLD);
	}
	
	protected View addSpace(int px) {
		View space = new View(this);
		getDetailsContainer().addView(space, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px));
		return space;
	}
	
	protected View addDivider() {
		View divider = new View(this);
		divider.setBackgroundColor(0xFF5E5E5E);
		getDetailsContainer().addView(divider, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
		return divider;
	}
	
	protected abstract ViewGroup getDetailsContainer();

}
