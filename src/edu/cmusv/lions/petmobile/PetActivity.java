package edu.cmusv.lions.petmobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public abstract class PetActivity extends Activity {

	protected boolean isNetworkConnected() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo != null;
	}

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
