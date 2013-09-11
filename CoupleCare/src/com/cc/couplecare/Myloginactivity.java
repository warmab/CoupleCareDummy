package com.cc.couplecare;

import android.app.Activity;
import android.content.Context;
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
		
		SharedPreferences settigns = getSharedPreferences("datos", Context.MODE_PRIVATE);
		String email = settigns.getString("mail", "");
		String pass = settigns.getString("password", "");
		}
	
	public void ejecutar(View view)
	{
		 String mail = etmail.getText().toString();
	     String passet = etpassword.getText().toString();
	     
		 SharedPreferences settigns = getSharedPreferences("datos", Context.MODE_PRIVATE);
		 String email = settigns.getString("mail", "");
		 String pass = settigns.getString("password", "");
        //finish();
       
		
		if(mail.equals(email) & passet.equals(pass)){
			Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)" + email + " y " + pass, Toast.LENGTH_LONG).show();
			home(view);
			}
		else
		{
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
		a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(a);
	}
	public void settings(View view){
		Intent a = new Intent(this,MysettingsActivity.class);
		startActivity(a);
	}
	

}
