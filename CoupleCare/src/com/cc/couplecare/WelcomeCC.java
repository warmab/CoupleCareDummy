package com.cc.couplecare;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class WelcomeCC extends Activity {
	public Button btnsign;
	public Button btnlogin;
	public Button btnstar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_cc);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome_cc, menu);
		return true;
	}
	
	public void lanzarsignup(View view){
		Intent i = new Intent(this, Mysignupactivity.class);
		startActivity(i);
	}
	
	public void lanzarlogin(View view){
		Intent a = new Intent(this,Myloginactivity.class);
		startActivity(a);
	}
	
	public void lanzarstart(View view){
		Intent a = new Intent(this,Myhomeactivity.class);
		startActivity(a);
	}
	
	

}
