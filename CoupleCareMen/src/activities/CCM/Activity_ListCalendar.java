package activities.CCM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import listcalendar.ListEntrance;
import listcalendar.List_Adapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import calculateDays.ColorEnum;
import calculateDays.Day;
import calculateDays.SimpleDate;

import com.loopj.android.http.AsyncHttpClient;

public class Activity_ListCalendar extends Activity {
	private ListView list;
	ImageButton btnnext, btnfirst;
	AsyncHttpClient client = new AsyncHttpClient();
	SessionManager session;
	String id = "";
	String datestart = "";
	String datefinish = "";
	String periodays = "";
	String cycleperiod1 = "";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		firstList();

	    }
	
	public void firstList(){
		SharedPreferences pref = getSharedPreferences("datawomanmen", Context.MODE_PRIVATE);
		final Resources res =getResources();
		String datestart = pref.getString("datestart", "");
		Log.d("datestart", datestart);
		int perioddays = pref.getInt("perioddays", 0);
		int cycleperiod = pref.getInt("durationcycle", 0);
		btnfirst =(ImageButton)findViewById(R.id.btnfirstcycle);
		btnfirst.setVisibility(View.INVISIBLE);
		btnnext = (ImageButton)findViewById(R.id.btnnextcycle);
		btnnext.setVisibility(View.VISIBLE);
		
		
		Calendar cal = GregorianCalendar.getInstance();
	    SimpleDateFormat formattext = new SimpleDateFormat("dd/MM/yyyy");    
	    Date date1 = null;
	    
	    try {
			date1=formattext.parse(datestart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	   cal.setTime(date1);
	   String datestartlist = pref.getString("datestart", "");
	   SharedPreferences pref2 = getSharedPreferences("actlist", Context.MODE_PRIVATE);// Se crea una segunda sharedpreferences
	   
	   Editor e = pref2.edit();
	   e.putString("newstartdatel",datestartlist); //Guardo la fecha actual de inicio
	   e.putInt("perioddays", perioddays);//guardo los dias de periodo
	   e.putInt("durationcycle", cycleperiod);//guardo la duracion del ciclo en la nueva shared
	   e.commit();
	   String fecha1 = ""+date1;
	   String periodd = ""+perioddays;
	   Log.d("Fecha",fecha1 + " "+ periodd);
	   
	   
	        ArrayList<ListEntrance> datos = new ArrayList<ListEntrance>();  
	        Day dia = new Day(cal,true,"hola",ColorEnum.BLUE);
	        dia.setCyclePeriod(cycleperiod);
	        dia.setPeriod(perioddays);
	        dia.calculateFertilesDays();
	       for(SimpleDate fecha : dia.getSimpleDatesList()) {
	        //Es esta parte 
	    	   //agregas un objeto de la clase ListEntrance
	    	   // ListEntrance es un adaptador de datos, ese manejo lo que yo le voy a mandar, en este caso, una imagen, el objeto
	    	   //fecha y aparte el color de esa fecha 
	        	datos.add(new ListEntrance(R.drawable.globosazul, fecha.toString(), fecha.getColor().toString()));
	        	//Al agregarlos a la lista se agregan directamente al Listview? o tienes que actualizar algo?
	        	//Aqui solo creo la lista esta es la lista de datos dentro de java
	        
	       }
	        
	        list = (ListView) findViewById(R.id.ListView_listado); //Aqui le hablo al listview de la vista que te mostres
	        list.setAdapter(new List_Adapter(this, R.layout.activity_listcalendar, datos){ //Aqui utilizo el adapter que se crea para manejar visual
				@SuppressLint("NewApi")
				@Override
				public void onEntrada(Object entrada, View view) {
					//al parecer no está entrando al if, o no tiene disponible el color rojo
					//correlo otra vez ok
					//entonces no está entrando
					
					
			        if (entrada != null) {
			        	
			            TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
			            if (texto_superior_entrada != null){  //te recomiendo que siempre abras llave, aunque sea 1 linea nomas
			            	texto_superior_entrada.setText(((ListEntrance) entrada).get_textoEncima());
			            	Typeface font = Typeface.createFromAsset(getAssets(), "CaviarDreamsBold.ttf"); //Poner CaviarDreams
			            	texto_superior_entrada.setTypeface(font);
			            }

			            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
			            if (texto_inferior_entrada != null){
			 
			            	texto_inferior_entrada.setText(((ListEntrance) entrada).get_textoDebajo()); 
			            	
			            	view.setBackgroundColor(Color.parseColor(((ListEntrance) entrada).get_textoDebajo()));//Cambio el color para que se note? si, pero esperate, deja cambio algo más
			            	texto_inferior_entrada.setVisibility(View.INVISIBLE);
			            	String colorstring = ((ListEntrance) entrada).get_textoDebajo();
			            	if(colorstring.equals("YELLOW")){
			            		view.setBackgroundColor(Color.rgb(255,255,77)); //250,250,77
			            	}	else if(colorstring.equals("GREEN")){
			            		view.setBackgroundColor(Color.rgb(32,178,170));
			            	}else if(colorstring.equals("CYAN")){
			            		view.setBackgroundColor(Color.rgb(100,149,237));
			            	}else if(colorstring.equals("RED")){
			            		view.setBackgroundColor(Color.rgb(255,69,0));
			            	}
			            		
			            	
			            	//view.setBackgroundColor(Color.rgb(new Color(0,0,0)));  //Amarillo 224,208,53, 71, 171, 49
			            }
			            
			  
			            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
			            if (imagen_entrada != null)
			            	imagen_entrada.setImageResource(((ListEntrance) entrada).get_idImagen());
			        }
				}
			});
	        

	        list.setOnItemClickListener(new OnItemClickListener() { 
				@Override
				public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
					ListEntrance elegido = (ListEntrance) pariente.getItemAtPosition(posicion); 

	                CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
	                Toast toast = Toast.makeText(Activity_ListCalendar.this, texto, Toast.LENGTH_LONG);
	                toast.show();
				}
	        });
		
	}
	
	
	public void next(View view) {
		SharedPreferences pref = getSharedPreferences("actlist",
				Context.MODE_PRIVATE);
		final Resources res = getResources();
		String datestart = pref.getString("newstartdatel", "");
		Log.d("newstartdatel", datestart);
		int perioddays = pref.getInt("perioddays", 0);
		int cycleperiod = pref.getInt("durationcycle", 0);
		btnnext = (ImageButton)findViewById(R.id.btnnextcycle);
		btnnext.setVisibility(View.INVISIBLE);
		btnfirst=(ImageButton)findViewById(R.id.btnfirstcycle);
		btnfirst.setVisibility(View.VISIBLE);
		

		Calendar cal = GregorianCalendar.getInstance();

		SimpleDateFormat formattext = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;

		try {
			date1 = formattext.parse(datestart);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cal.setTime(date1);
		cal.add(Calendar.DAY_OF_MONTH, cycleperiod);
		Log.d("cycle", "" + cycleperiod);
		String fecha1 = "" + date1;
		String periodd = "" + perioddays;
		Log.d("Fecha", fecha1 + " " + periodd + cal.toString());

		ArrayList<ColorEnum> colorlist = new ArrayList<ColorEnum>();
		ArrayList<ListEntrance> datos = new ArrayList<ListEntrance>();
		Day dia = new Day(cal, true, "hola", ColorEnum.BLUE);
		dia.setCyclePeriod(cycleperiod);
		dia.setCalendar(cal);
		dia.setPeriod(perioddays);
		dia.calculateFertilesDays();
		for (SimpleDate fecha : dia.getSimpleDatesList()) {

			datos.add(new ListEntrance(R.drawable.globosazul, fecha.toString(),
					fecha.getColor().toString()));
			colorlist.add(fecha.getColor());
			// Toast.makeText(getApplicationContext(),
			// fecha.getColor().toString(), Toast.LENGTH_LONG).show();
		}

		list = (ListView) findViewById(R.id.ListView_listado);
		list.setAdapter(new List_Adapter(this, R.layout.activity_listcalendar,
				datos) {

			@Override
			public void onEntrada(Object entrada, View view) {
				if (entrada != null) {
					TextView texto_superior_entrada = (TextView) view
							.findViewById(R.id.textView_superior);
					if (texto_superior_entrada != null) {
						texto_superior_entrada.setText(((ListEntrance) entrada)
								.get_textoEncima());
						Typeface font = Typeface.createFromAsset(getAssets(), "CaviarDreamsBold.ttf"); //Poner CaviarDreams
		            	texto_superior_entrada.setTypeface(font);
					}
					TextView texto_inferior_entrada = (TextView) view
							.findViewById(R.id.textView_inferior);
					if (texto_inferior_entrada != null) {
						texto_inferior_entrada.setText(((ListEntrance) entrada)
								.get_textoDebajo());
						
						texto_inferior_entrada.setVisibility(View.INVISIBLE);
						view.setBackgroundColor(Color.parseColor(((ListEntrance) entrada).get_textoDebajo()));//Cambio el color para que se note? si, pero esperate, deja cambio algo más
		            	
		            	String colorstring = ((ListEntrance) entrada).get_textoDebajo();
		            	if(colorstring.equals("YELLOW")){
		            		view.setBackgroundColor(Color.rgb(255,255,77)); //250,250,77
		            	}	else if(colorstring.equals("GREEN")){
		            		view.setBackgroundColor(Color.rgb(32,178,170));
		            	}else if(colorstring.equals("CYAN")){
		            		view.setBackgroundColor(Color.rgb(100,149,237));
		            	}else if(colorstring.equals("RED")){
		            		view.setBackgroundColor(Color.rgb(255,69,0));
		            	}
						
					}
					ImageView imagen_entrada = (ImageView) view
							.findViewById(R.id.imageView_imagen);
					if (imagen_entrada != null) {
						imagen_entrada
								.setImageResource(((ListEntrance) entrada)
										.get_idImagen());
					}
				}
			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pariente, View view,
					int posicion, long id) {
				ListEntrance elegido = (ListEntrance) pariente
						.getItemAtPosition(posicion);

				CharSequence texto = "Seleccionado: "
						+ elegido.get_textoDebajo();
				Toast toast = Toast.makeText(Activity_ListCalendar.this, texto,
						Toast.LENGTH_LONG);
				toast.show();
			}
		});
		/* Guardo nueva fecha */
		String newdate = formattext.format(date1);
		SharedPreferences pref1 = getSharedPreferences("actlist",
				Context.MODE_PRIVATE);
		Editor e = pref1.edit();
		e.putString("newstartdatel", newdate);
		e.commit();

	}
	
	private long mLastPress = 0;

	@Override
	public void onBackPressed() {
		Toast onBackPressedToast = Toast.makeText(this,
				"Use the home button", Toast.LENGTH_SHORT);
		long currentTime = System.currentTimeMillis();
		if (currentTime - mLastPress > Toast.LENGTH_LONG) {
			onBackPressedToast.show();
			mLastPress = currentTime;
		} else {
			onBackPressedToast.cancel(); // Difference with previous answer.
											// Prevent continuing showing toast
											// after application exit
			super.onBackPressed();
		}
	}

	public void backlist(View view){
		firstList();	
	}
	
	public void home(View view){
		Intent i = new Intent(this,Activity_ppalview.class);
		startActivity(i);
		finish();
	}
	


}
