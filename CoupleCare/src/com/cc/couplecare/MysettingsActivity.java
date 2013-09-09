package com.cc.couplecare;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MysettingsActivity extends Activity {
	
	Spinner ListCicle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		ListCicle = (Spinner)findViewById(R.id.spinner1);
		String[] options = {"21","22","23","24","25","26","27","28","29","30","31","32",
				"33","34","35"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,options);
				ListCicle.setAdapter(adapter);
		
			
		}
	
	
	@SuppressLint("NewApi")
	public void showDatePickerDialog(View v){
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "Period Beginning");
	}

	public void login(View view){
		Intent i = new Intent(this,Myloginactivity.class);
		startActivity(i);
		
	}
	

	public void calendar(View view){
		Intent i = new Intent(this,MyCalendarActivity.class);
		startActivity(i);
		
	}
	
	
	
	public void recuperarlist(View view){
		//Recuperar el dato del spinner
	}
	
}
