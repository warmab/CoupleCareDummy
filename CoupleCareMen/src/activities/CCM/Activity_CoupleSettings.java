package activities.CCM;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Activity_CoupleSettings extends Activity {

	EditText emailw, syncpass;
	ImageButton btnenter;
	// Session Manager Class
	SessionManager session;
	AsyncHttpClient client = new AsyncHttpClient();
	String datestart = "";
	String datefinish = "";
	String periodays = "";
	String cycleperiod = "";

	Gson g = new Gson();
	public final static String KEY_NAME = "";

	String idw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_couplesettings);

		emailw = (EditText) findViewById(R.id.etemailw);
		syncpass = (EditText) findViewById(R.id.etsynpass);
		// Session Manager
		session = new SessionManager(getApplicationContext());

	}

	public void execute(View v) {

		String email = emailw.getText().toString();
		if (email.matches("")) {
			Toast.makeText(this, "You did not enter a Email",
					Toast.LENGTH_SHORT).show();
			return;
		}

		String spass = syncpass.getText().toString();
		if (email.matches("")) {
			Toast.makeText(this, "You did not enter a Sync Pass",
					Toast.LENGTH_SHORT).show();
			return;
		}

		SharedPreferences pref = getSharedPreferences("prefcouple",
				Context.MODE_PRIVATE);
		Editor e = pref.edit();
		e.putString("emailwoman", email);
		e.putString("SyncPass", spass);
	}

	public void callppalview(View v) {

		// Se asigna la URL del JSON alojado en Internet
		client.get(
				"http://couplecare.us/couplecare/backend.php?action=7&username="
						+ emailw.getText().toString() + "&password="
						+ syncpass.getText().toString(),
				new AsyncHttpResponseHandler() {
					// En caso de que haya una respuesta
					@Override
					public void onSuccess(String response) {
						response = response.replace(" ", "");
						if (!response.equals("")) {
							session.createLoginSession(response, ""); // Guardo
																		// id en
																		// el
																		// objeto
																		// session
							Toast.makeText(getApplicationContext(),
									"Starting Session" + response,
									Toast.LENGTH_LONG).show();
							// Staring MainActivity
							// Crear alertdialog para actualizar informacion
							idw = response;
							Log.d("idw", idw);
						} else {
							Toast.makeText(getApplicationContext(),
									"Email or Password Incorrect ",
									Toast.LENGTH_LONG).show();
						}
					}
				});

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Optional");
		builder.setMessage("Don't forget update your data");
		builder.setCancelable(false);
		// Add the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// Se asigna la URL del JSON alojado en Internet
				client.get(
						"http://couplecare.us/couplecare/data/data_"
								+ idw + ".json",
						new AsyncHttpResponseHandler() {
							// En caso de que haya una respuesta
							@Override
							public void onSuccess(String response) {
								Gson g = new Gson();
								datos json = g.fromJson(response, datos.class); // del
																				// json
																				// que
																				// tenemos
																				// convertirlo
																				// en
																				// un
																				// arreglo
								Log.d("response", response);
								response = response.replace(" ", "");

								datestart = json.DateStart;
								Log.d("datestart", datestart);
								datefinish = json.DateFinish;
								periodays = json.PeriodDays;
								int periodd = Integer.parseInt(periodays);
								cycleperiod = json.DurationCycle;
								int percycle = Integer.parseInt(cycleperiod);

								// Agregar un cliente nuevo que refresque el
								// JSON, ejecuta el
								// backend php

								SharedPreferences prefmen = getSharedPreferences(
										"datawomanmen", Context.MODE_PRIVATE);
								Editor e = prefmen.edit();
								e.putString("datestart", datestart);
								e.putString("datefinish", datefinish);
								e.putInt("periodays", periodd);
								e.putInt("cycleperiod", percycle);
								e.commit();
								Toast.makeText(getApplicationContext(),
										"Data saved", Toast.LENGTH_SHORT)
										.show();
								Intent i = new Intent(getApplicationContext(),
										Activity_ppalview.class);
								startActivity(i);
							}
						});
			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent i = new Intent(getApplicationContext(),
								Activity_ppalview.class);
						startActivity(i);
						Toast.makeText(getApplicationContext(), "Don´t exist conecction", Toast.LENGTH_SHORT).show();
						Toast.makeText(getApplicationContext(), "Don´t forget push the update button", Toast.LENGTH_SHORT).show();
					}
				});
		builder.show();
		// Create the AlertDialog
		AlertDialog dialog = builder.create();
	}

}