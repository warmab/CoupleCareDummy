package com.cc.couplecare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Myloginactivity extends Activity {
	private static final boolean USER_IS_GOING_TO_EXIT = false;
	EditText etmail, etpassword;
	TextView txt3;
	Button btncalcular;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		
		etmail = (EditText) findViewById(R.id.etmaillog);
		etpassword = (EditText) findViewById(R.id.etpasslog);
		txt3 = (TextView) findViewById(R.id.textView3);
		btncalcular = (Button) findViewById(R.id.btncalcular);
		
		SharedPreferences settigns = getSharedPreferences("datos", Context.MODE_PRIVATE);
		String email = settigns.getString("mail", "");
		String pass = settigns.getString("password", "");
		
		SharedPreferences pref = getSharedPreferences("datos", Context.MODE_PRIVATE);
		Boolean datos = pref.getBoolean("dataexist", false);
		if(datos.equals(true)){
			txt3.setVisibility(View.INVISIBLE);
			btncalcular.setVisibility(View.INVISIBLE);
		}else{
			txt3.setVisibility(View.VISIBLE);
			btncalcular.setVisibility(View.VISIBLE);
		}
		
	
		}
	
	/*public void checkdatasign(View view){
		SharedPreferences pref = getSharedPreferences("datos", Context.MODE_PRIVATE);
		Boolean datos = pref.getBoolean("data", false);
		if(datos.equals(true)){
			txt3.setVisibility(View.INVISIBLE);
			btncalcular.setVisibility(View.INVISIBLE);
			
		}
	}*/
	
	public void ejecutar(View view)
	{
		 String mail = etmail.getText().toString();
	     String passet = etpassword.getText().toString();
	     
		 SharedPreferences settigns = getSharedPreferences("datos", Context.MODE_PRIVATE);
		 String email = settigns.getString("mail", "");
		 String pass = settigns.getString("password", "");
        //finish();
       
		
		if(mail.equals(email) && passet.equals(pass)){
			Toast.makeText(getApplicationContext(), "Hello" + email + " y " + pass, Toast.LENGTH_LONG).show();
			Boolean logged = true;
			Editor editor = settigns.edit();
			editor.putBoolean("logged", logged);	
			editor.commit();
			home(view);
		}
		else if(mail!=email && passet.equals(pass) )
		{
			Toast.makeText(getApplicationContext(), "Email is incorrect", Toast.LENGTH_LONG).show();
		}else if(passet!=pass && mail.equals(email))
		{
			Toast.makeText(getApplicationContext(), "Password is incorrect" , Toast.LENGTH_LONG).show();
		}else if(mail!=email && passet!=pass){
			Toast.makeText(getApplicationContext(), "you're not my owner" , Toast.LENGTH_LONG).show();
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
	
	
	/*Use the physics button back.
	 * */
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
