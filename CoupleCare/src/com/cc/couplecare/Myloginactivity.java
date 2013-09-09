package com.cc.couplecare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Myloginactivity extends Activity {
	EditText etmail, etpassword;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		
		etmail = (EditText) findViewById(R.id.etmaillog);
		etpassword = (EditText) findViewById(R.id.etpasslog);
		}
	
	public void ejecutar()
	{
		SharedPreferences settigns = getSharedPreferences("preferences", 0);
		String email = settigns.getString("email", "");
		String pass = settigns.getString("pass", "");
        finish();
		Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)" + email + " y " + pass, Toast.LENGTH_LONG).show();
		if(email == etmail.toString()&& pass == etpassword.toString()){
			Intent c = new Intent(this,Myhomeactivity.class);
			startActivity(c);
		}
		else{
			finish();
		}	
	}
	
	public void start(View view){
		Intent i = new Intent(this,Myhomeactivity.class);
		startActivity(i);
		
	}

	public void signup(View view){
		Intent i = new Intent(this,Mysignupactivity.class);
		startActivity(i);
	}
	
	public void home(View view){
		Intent a = new Intent(this,Myhomeactivity.class);
		startActivity(a);
	}
	public void settings(View view){
		Intent a = new Intent(this,MysettingsActivity.class);
		startActivity(a);
	}

}
