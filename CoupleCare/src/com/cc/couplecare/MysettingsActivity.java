package com.cc.couplecare;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MysettingsActivity extends Activity {
	
	private static final boolean USER_IS_GOING_TO_EXIT = false;
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
				ListCicle.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
							int position, long id){
						Toast.makeText(parentView.getContext(), "You selected " + parentView.getItemAtPosition(position).toString(),
								Toast.LENGTH_LONG).show();
					}
					
					public void onNothingSelected(AdapterView<?> parentView){
						
					}
				});
			
		
			
		}
	
	
	@SuppressLint("NewApi")
	public void showDatePickerDialog(View v){
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "Period Beginning");
		
	}
	
	  public void onDateSet(DatePickerFragment view, int year, int month, int day) {
		  SharedPreferences cycledate = getSharedPreferences("datacycle", Context.MODE_PRIVATE);
		  Editor editor = cycledate.edit();
		  editor.putInt("year", year);
		  editor.putInt("month", month);
		  editor.putInt("day", day);
		  editor.commit();
		  finish();
	    }
	  
	  public void setStartCycle(){
		  
		  
	  }

	public void login(View view){
		Intent i = new Intent(this,Myloginactivity.class);
		startActivity(i);
		
	}
	

	public void calendar(View view){
		Intent i = new Intent(this,MyCalendarActivity.class);
		startActivity(i);
		
	}
	
	@Override
	public void onBackPressed() {
		Context context = getApplicationContext();
		CharSequence text = "Tap again to exit.";
		int duration = Toast.LENGTH_SHORT;

		Toast backtoast = Toast.makeText(context, text, duration);
		backtoast.show();
		
	    if(USER_IS_GOING_TO_EXIT) {
	        if(backtoast!=null&&backtoast.getView().getWindowToken()!=null) {
	            finish();
	        } else {
	            backtoast = Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT);
	            backtoast.show();
	        }
	    } else {
	        //other stuff...
	        super.onBackPressed();
	    }
	}
	
	
	
	public void recuperarlist(View view){
		//Recuperar el dato del spinner
	}
	
}
