package com.cc.couplecare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeCC extends Activity {
	private static final boolean USER_IS_GOING_TO_EXIT = false;
	public Button btnsign;
	public Button btnlogin;
	public Button btnstar;
	public TextView tv1;
	
	
	
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
	
	public void checkdata(View view){
		SharedPreferences pref1 = getSharedPreferences("datos", MODE_PRIVATE);
		String datos = pref1.getString("data", "");
		if(datos.equals("true")){
			lanzarlogin(view);
		}else{
			lanzarsignup(view);
		}
	}
	
	
	public void lanzarsignup(View view){
		Intent i = new Intent(this, Myloginactivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
	
	public void lanzarlogin(View view){
		Intent a = new Intent(this,Myloginactivity.class);
		a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(a);
	}
	
	public void lanzarstart(View view){
		Intent a = new Intent(this,Myhomeactivity.class);
		a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(a);
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
