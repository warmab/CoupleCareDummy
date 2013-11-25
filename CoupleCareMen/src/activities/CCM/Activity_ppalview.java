package activities.CCM;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import listcalendar.ListEntrance;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import calculateDays.ColorEnum;
import calculateDays.Day;
import calculateDays.SimpleDate;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Activity_ppalview extends Activity {

	// Se crean objetos de las clases a usar AsyncHttpClient que nos permitirá
	// conectarnos
	// al archivo .json en Internet y el GSON que nos permitirá leer el JSON
	AsyncHttpClient client = new AsyncHttpClient();
	Gson g = new Gson();
	public final static String KEY_NAME = "";
	SessionManager session;
	ImageButton logout;

	String datestart = "";
	String datefinish = "";
	String periodays = "";
	String cycleperiod = "";
	public String idW, response2 = "0";
	ImageButton btnfertil;
	TextView tvdate;
	String idw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ppalview);
		btnfertil = (ImageButton) findViewById(R.id.btnfertil);
		logout = (ImageButton) findViewById(R.id.logout);
		tvdate = (TextView) findViewById(R.id.tvdate);

		session = new SessionManager(getApplicationContext());

		session.checkLogin();

		HashMap<String, String> user = session.getUserDetails();
		// id
		String id = "";
		id = user.get(SessionManager.KEY_NAME);
		Toast.makeText(getApplicationContext(), "ID SESSION " + id,
				Toast.LENGTH_LONG).show();

		idW = id;

	

		// Se asigna la URL del JSON alojado en Internet
		client.get("http://couplecare.us/couplecare/data/data_" + id
				+ ".json", new AsyncHttpResponseHandler() {
			// En caso de que haya una respuesta
			@Override
			public void onSuccess(String response) {
				// Se usa el GSON pasandole los paremetros recibido a la clase
				// numero en donde tendremos nuestras
				// variables en donde los valores serán pasados desde el JSON.
				datos json = g.fromJson(response, datos.class); // del jason que
																// tenemos
																// conviertelo
																// en un arreglo
				response = response.replace(" ", "");
				if (json.ID.equals("0")) {
					Toast.makeText(getApplicationContext(),
							"Username or Password Incorrect ",
							Toast.LENGTH_LONG).show();
					Intent i = new Intent(getApplicationContext(),
							Activity_CoupleSettings.class);
					startActivity(i);
				}
				// Si el permiso esta bloqueado manda mensaje
				if (!json.EmailM.equals("")) {
					// Toast.makeText(getApplicationContext(),
					// "Email del hombre: "+json.EmailM,
					// Toast.LENGTH_LONG).show();
				}

			}
		});

	}
	
	public void dateballon() {
		tvdate = (TextView) findViewById(R.id.tvdate);
		btnfertil = (ImageButton) findViewById(R.id.btnfertil);

		ArrayList<Integer> diasList = new ArrayList<Integer>();
		ArrayList<Integer> mesList = new ArrayList<Integer>();
		ArrayList<ColorEnum> colorlist = new ArrayList<ColorEnum>();
		// Obtenemos la fecha actual
		Calendar ahoraCal = Calendar.getInstance();
		int dia = ahoraCal.get(Calendar.DAY_OF_MONTH);
		int mes = ahoraCal.get(Calendar.MONTH);
		mes = mes + 1; // Se le suma uno al mes para obtener bien el numero del
						// mes
		int anio = ahoraCal.get(Calendar.YEAR);
		String fecha = dia + "/" + mes + "/" + anio;
		// Se pone la actual en el textview
		tvdate.setText(fecha);
		// Obtengo los datos de la sharedpreferences
		SharedPreferences pref = getSharedPreferences("datawomanmen",
				Context.MODE_PRIVATE);
		String datestart = pref.getString("datestart", "");
		int durationcycle = pref.getInt("cycleperiod", 0); 
		int perioddays = pref.getInt("periodays", 0);
		// Crear objeto Day
		Calendar callist = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;
		try {
			date1 = format.parse(datestart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		callist.setTime(date1);

		ArrayList<ListEntrance> datos = new ArrayList<ListEntrance>();
		Day diaobj = new Day(callist, true, "hola", ColorEnum.BLUE);
		diaobj.setCyclePeriod(durationcycle);
		diaobj.setCalendar(callist);
		diaobj.setPeriod(perioddays);
		diaobj.calculateFertilesDays();
		int cont = 0;

		for (SimpleDate fechalist : diaobj.getSimpleDatesList()) {

			datos.add(new ListEntrance(R.drawable.globosazul, fechalist
					.toString(), fechalist.getColor().toString()));
			// Toast.makeText(getApplicationContext(),
			// fechalist.toString()+fechalist.getColor().toString(),
			// Toast.LENGTH_SHORT).show();
			diasList.add(fechalist.getDay());
			// Toast.makeText(getApplicationContext(),
			// (dia==fechalist.getDay())+"", Toast.LENGTH_SHORT).show();
			mesList.add(fechalist.getMonth());
			// Toast.makeText(getApplicationContext(),
			// (mes+" "+fechalist.getMonth()), Toast.LENGTH_SHORT).show();
			colorlist.add(fechalist.getColor());
			 //Toast.makeText(getApplicationContext(), colorlist.toString(),
			 //Toast.LENGTH_SHORT).show();
			 
			if (dia == fechalist.getDay() && mes == fechalist.getMonth()) {

				// Log.d("DiaColor",
				// "#"+colorlist.get(cont).toString()+ColorEnum.CYAN+"*");
			
				
			
				
				if (fechalist.getColor().toString()
						.equals("CYAN")) {
					// Log.d("DiaColor", colorlist.toString());
					btnfertil.setImageResource(R.drawable.imgperioddaya);
				} if (fechalist.getColor().toString()
						.equals("YELLOW")) {
					btnfertil.setImageResource(R.drawable.imgfertil);
				} if (fechalist.getColor().toString()
						.equals("GREEN")) {
					
					btnfertil.setImageResource(R.drawable.imglesfertil);
				} if (fechalist.getColor().toString()
						.equals("RED")) {
					btnfertil.setImageResource(R.drawable.imgmostfertil);

				}
				
				
			}

		}
	}
	
	

	public void logout(View view) {
		client.get(
				"http://couplecare.us/couplecare/backend.php?action=8&id="
						+ idW, new AsyncHttpResponseHandler() {
					// En caso de que haya una respuesta
					@Override
					public void onSuccess(String response) {
						if (!response.equals("0"))
							Toast.makeText(getApplicationContext(),
									"Shutting down" + idW, Toast.LENGTH_LONG)
									.show();
						else
							Toast.makeText(getApplicationContext(),
									"Can´t shutting down" + idW, Toast.LENGTH_LONG)
									.show();
					}
				});
		session.logoutUser();
	}

	public void callclienthtt(View v) {
		// Se asigna la URL del JSON alojado en Internet
		client.get("http://greenlifemagazine.com.mx/couplecare/data/data_"
				+ idw + ".json", new AsyncHttpResponseHandler() {
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
				Toast.makeText(getApplicationContext(), "Data saved",
						Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(),
						Activity_ppalview.class);
				startActivity(i);
			}
		});
	}

	public void calllistcalendar(View v) {
		Intent i = new Intent(this, Activity_ListCalendar.class);
		startActivity(i);
		finish();
	}

	public void callcouplesett(View v) {
		Intent i = new Intent(this, Activity_CoupleSettings.class);
		startActivity(i);
		finish();
	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		dateballon();
		// Toast.makeText(getApplicationContext(), "Hola aqui estoy onResume",
		// Toast.LENGTH_LONG).show();
		
	}


}
