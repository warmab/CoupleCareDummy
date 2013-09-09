package com.cc.couplecare;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Signupmen extends Activity {

	EditText etemailsm, etpasssm, etpassvsm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signupmen);
		
		etemailsm = (EditText)findViewById(R.id.etmailsm);
		etpasssm = (EditText)findViewById(R.id.etpasssm);
		etpassvsm = (EditText)findViewById(R.id.etpassver);
		
		SharedPreferences  prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
		etemailsm.setText(prefe.getString("email", ""));
		
	}
	
	public void ejecutar(View view)
	{
		SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
		Editor editor = preferencias.edit();
		editor.putString("emailc", etemailsm.getText().toString());
		editor.putString("pass", etpasssm.getText().toString());
		editor.putString("passv", etpassvsm.getText().toString());
		editor.commit();
		finish();
		
	}

}
