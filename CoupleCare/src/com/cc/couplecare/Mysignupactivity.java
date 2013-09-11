package com.cc.couplecare;

import com.cc.couplecare.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Mysignupactivity extends Activity {
	private EditText etmail,etpass,etpassv;
	
	public static final String IS_LOGIN = "IsLoggedIn";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		etmail = (EditText)findViewById(R.id.etmaillog);
		etpass= (EditText)findViewById(R.id.etpass);
		etpassv = (EditText)findViewById(R.id.etpassv);
		
		SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
		etmail.setText(prefe.getString("mail",""));
		etpass.setText(prefe.getString("pass",""));
		etpassv.setText(prefe.getString("passv",""));
		
	
	}
	
	public void ejecutar(View view)
	{
		String email = etmail.getText().toString();
		String pass1 = etpass.getText().toString();
		String passv = etpassv.getText().toString();
		
		if(pass1.equals(passv)){
			SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
			Editor editor = preferencias.edit();
			editor.putString("mail", email);
			editor.putString("password", pass1);
			editor.putString("verify", passv);
			editor.commit();
			//finish();
		}else{
			
			Context context = getApplicationContext();
			CharSequence text = "Passwords are different.";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		
		
		
		AlertDialog.Builder dialogosm = new AlertDialog.Builder(this);
		dialogosm.setTitle("Optional");
		dialogosm.setMessage("¿Would you like to register your partner?");
		dialogosm.setCancelable(false);
		dialogosm.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog1, int id) {
				// TODO Auto-generated method stub
				launchIntent();
			}
		}); 
		dialogosm.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		dialogosm.show();
	}
			
	private void launchIntent(){
		Intent it = new Intent(this,Signupmen.class);
	    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(it);
	}
	
	
	
	public void aceptar(View view)
	{
		Intent i = new Intent(this, Signupmen.class);
		startActivity(i);
		
	}
	
	public void cancel(View view)
	{
		finish();
	}

}


