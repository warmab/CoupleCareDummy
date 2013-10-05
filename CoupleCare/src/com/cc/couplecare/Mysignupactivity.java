package com.cc.couplecare;

import utils.Validations;

import com.cc.couplecare.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mysignupactivity extends Activity {
	private EditText etmail, etpass, etpassv;

	private static final boolean USER_IS_GOING_TO_EXIT = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		etmail = (EditText) findViewById(R.id.etmaillog);
		etpass = (EditText) findViewById(R.id.etpass);
		etpassv = (EditText) findViewById(R.id.etpassv);
		// txtv4 = (TextView) findViewById(R.id.txtv4);

		SharedPreferences prefsignp = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		etmail.setText(prefsignp.getString("mail", ""));
		etpass.setText(prefsignp.getString("pass", ""));
		etpassv.setText(prefsignp.getString("passv", ""));
		// txtv4.setText(prefsignp.getString("data", ""));

		/*
		 * if (!isOnline(this)) { Toast.makeText(getBaseContext(),
		 * "Test the conecttion", Toast.LENGTH_SHORT).show(); this.finish(); }
		 * else { Toast.makeText(getBaseContext(), "You´re Online ",
		 * Toast.LENGTH_SHORT).show(); this.finish(); } // String ovi =
		 * val.isChuleta();
		 * 
		 * /* Context context = getApplicationContext(); // CharSequence text =
		 * ; int duration = Toast.LENGTH_SHORT;
		 * 
		 * Toast toast = Toast.makeText(context, ovi, duration); toast.show();
		 */

	}

	// Validate exist Online Conecction
	public boolean isOnline(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) {
			return true;
		}
		return false;
	}

	@SuppressLint("CommitPrefEdits")
	public void ejecutar(View view) {
		String email = etmail.getText().toString();
		String pass1 = etpass.getText().toString();
		String passv = etpassv.getText().toString();
		// String data = "";
		
		SharedPreferences prefsignp1 = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		Editor editor = prefsignp1.edit();

		if (pass1.equals(passv)) {
			
			editor.putString("mail", email);
			editor.putString("password", pass1);
			// editor.putString("verify", passv);
			editor.putBoolean("dataexist", true);
			editor.commit();
			// finish();
			
			AlertDialog.Builder dialogosm = new AlertDialog.Builder(this);
			dialogosm.setTitle("Optional");
			dialogosm.setMessage("¿Would you like to register your partner?");
			dialogosm.setCancelable(false);
			dialogosm.setPositiveButton("Confirm",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog1, int id) {
						
							launchSignUM();
							
						}
					});
			dialogosm.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							SharedPreferences prefsignp2 = getSharedPreferences("datos",
									Context.MODE_PRIVATE);
							Editor editor = prefsignp2.edit();
							editor.putBoolean("Couplexist", false);
							launchHome();
						}
					});
			dialogosm.show();
			
		} else {
			Context context = getApplicationContext();
			CharSequence text = "Passwords are different.";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();

			SharedPreferences prefsignp3 = getSharedPreferences("datos",
					Context.MODE_PRIVATE);
			Editor editor2 = prefsignp3.edit();
			editor2.putBoolean("dataexist", false);
			

		}

		
	}

	/*private void dialogPartner() {
		AlertDialog.Builder dialpartner = new AlertDialog.Builder(this);
		dialpartner.setTitle("New Partner");
		dialpartner.setMessage("¿Do you have a partner?");
		dialpartner.setCancelable(false);
		dialpartner.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						SharedPreferences pref = getSharedPreferences("datos",
								MODE_PRIVATE);
						Editor editor = pref.edit();
						String yes = "yes";
						editor.putString("partner", yes);
						launchSignUM();
					}
				});

		dialpartner.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						launchHome();

					}
				});

	}*/
	
	


	private void launchSignUM() {
		Intent it = new Intent(this, Signupmen.class);
		it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(it);
	}

	private void launchHome() {
		Intent i = new Intent(this, Myhomeactivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}

	@Override
	public void onBackPressed() {
		Context context = getApplicationContext();
		CharSequence text = "Tap again to exit.";
		int duration = Toast.LENGTH_SHORT;

		Toast backtoast = Toast.makeText(context, text, duration);
		backtoast.show();

		if (USER_IS_GOING_TO_EXIT) {
			if (backtoast != null
					&& backtoast.getView().getWindowToken() != null) {
				finish();
			} else {
				backtoast = Toast.makeText(this, "Press back to exit",
						Toast.LENGTH_SHORT);
				backtoast.show();
			}
		} else {
			// other stuff...
			super.onBackPressed();
		}
	}

	public void aceptar(View view) {
		Intent i = new Intent(this, Signupmen.class);
		startActivity(i);

	}

	public void cancel(View view) {
		finish();
	}

}
