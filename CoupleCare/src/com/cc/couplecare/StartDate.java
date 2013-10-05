package com.cc.couplecare;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartDate extends Activity {
	DatePicker pickerDate;
	TextView info;
	EditText cycleperiod;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startdate);
		
		info = (TextView) findViewById(R.id.info);
		pickerDate = (DatePicker) findViewById(R.id.pickerdate);
		Calendar today = Calendar.getInstance();
		cycleperiod = (EditText) findViewById(R.id.cycleperiod);
		pickerDate.init
		(today.get(Calendar.YEAR),
		today.get(Calendar.MONTH), 
		today.get(Calendar.DAY_OF_MONTH),
		new OnDateChangedListener(){
			
			@Override
			public void onDateChanged(DatePicker view,
					int year, int monthOfyear, int dayOfMonth){
				Toast.makeText(getApplicationContext(), "OnDateChanged", Toast.LENGTH_LONG).show();
				info.setText("Year: " + year + "\n" + 
				"Month of Year: " + monthOfyear + "\n" +
				"Day of Month: " + dayOfMonth);
				String datestart = dayOfMonth+"/"+monthOfyear+"/"+year;
				int cyclenumber = Integer.parseInt(cycleperiod.getText().toString());
			
				SharedPreferences pref = getSharedPreferences("datos",Context.MODE_PRIVATE);
				Editor editor = pref.edit();
				editor.putInt("durationcycle", cyclenumber);
				editor.putInt("year", year);
				editor.putInt("month", monthOfyear);
				editor.putInt("day", dayOfMonth);
				editor.putString("datestart",datestart );
				editor.commit();
			}
			
		});
		
   }
	
	
	/*public void savedinShared(int year, int monthOfYear, int dayOfMonth){
		SharedPreferences pref = getSharedPreferences("datos",Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putInt("year", year);
		editor.putInt("monthOfyear", monthOfYear);
		editor.putInt("dayOfMonth", dayOfMonth);
		editor.commit();
		}*/
	
	
	

}
