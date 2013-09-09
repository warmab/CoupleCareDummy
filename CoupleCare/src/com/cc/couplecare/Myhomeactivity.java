package com.cc.couplecare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Myhomeactivity extends Activity {

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
	
	

}
