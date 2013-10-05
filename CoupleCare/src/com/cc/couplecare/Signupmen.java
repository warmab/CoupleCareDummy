package com.cc.couplecare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signupmen extends Activity {

	private static final boolean USER_IS_GOING_TO_EXIT = false;
	EditText etemailsm, etpasyncm, etpasyncvm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signupmen);
		
		etemailsm = (EditText)findViewById(R.id.etmailsm);
		etpasyncm = (EditText)findViewById(R.id.etpasssm);
		etpasyncvm = (EditText)findViewById(R.id.etpassver);
		
		
		SharedPreferences  prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
		etemailsm.setText(prefe.getString("emailmen", ""));
		etpasyncm.setText(prefe.getString("passync", ""));
		etpasyncm.setText(prefe.getString("pasyncv", ""));
		//etpassvsm.setText(prefe.getString("passv", ""));
		
	}
	
	public void ejecutar(View view)
	{
		SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
		Editor editor = preferencias.edit();
		
		String mail = etemailsm.getText().toString();
		String pass = etpasyncm.getText().toString();
		String passv = etpasyncvm.getText().toString();
		
		
		if(pass.equals(passv)){
			editor.putString("emailmen", mail);
			editor.putString("passync", pass);
			editor.putString("pasyncv", passv);
			editor.putString("CoupleMenData", "Yes");
			//editor.putString("passv", passv);
			editor.commit();
			//finish();
			}else{
				Context context = getApplicationContext();
				CharSequence text = "Passwords are different.";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				editor.putString("CoupleMenData", "No");
			}
		
		AlertDialog.Builder dialogosm = new AlertDialog.Builder(this);
		dialogosm.setTitle("Couple");
		dialogosm.setMessage("Thank's for register your partner");
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
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}

	private void launchIntent(){
		Intent it = new Intent(this, Myhomeactivity.class);
	    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(it);
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


