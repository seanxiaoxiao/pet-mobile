package edu.cmusv.lions.petmobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public abstract class PetActivity extends Activity {

	protected void showMessageDialog(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title).setMessage(message);
		builder.setPositiveButton("Close", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// no-op
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
