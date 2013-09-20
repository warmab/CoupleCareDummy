package com.cc.couplecare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Myhomeactivity extends Activity {

	private static final boolean USER_IS_GOING_TO_EXIT = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homenothing);
	}
	
	public void lanzarlogin(View view)
	{
		Intent i = new Intent(this, Myloginactivity.class);
		startActivity(i);
	}
	
	public void lanzarsync(View view)
	{
		Intent a = new Intent(this,Signupmen.class);
		startActivity(a);
	}
	
	public void lanzarcalendar(View view)
	{
		Intent a = new Intent(this, MyCalendarActivity.class);
		startActivity(a);
	}
	
	public void lanzarsettings(View view){
		Intent b = new Intent(this, MysettingsActivity.class);
		startActivity(b);
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
	
	

}
