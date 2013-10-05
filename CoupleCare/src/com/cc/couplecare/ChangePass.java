package com.cc.couplecare;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangePass extends Activity {
	EditText etpassold,etpassnew, etpassver, etemail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepass);
		etemail = (EditText)findViewById(R.id.etmail);
		etpassold = (EditText)findViewById(R.id.etapass);
		etpassnew = (EditText) findViewById(R.id.etnpass);
		etpassver = (EditText) findViewById(R.id.etvernpass);
		
		
		SharedPreferences info = getSharedPreferences("datos", Context.MODE_PRIVATE);
		etemail.setText(info.getString("email", ""));
		etpassold.setText(info.getString("pass",""));
	}
	
	public void changes(View view){
		String email = etemail.getText().toString();
		String pass1 = etpassold.getText().toString();
		
		SharedPreferences info1 = getSharedPreferences("datos", Context.MODE_PRIVATE);
		String emailshared = info1.getString("email", "");
		String passshared = info1.getString("pass", "");
		if(email.equals(emailshared) && pass1.equals(passshared)){
			
			String newpass = etpassnew.getText().toString();
			String verpass = etpassver.getText().toString();
			
			if(newpass.equals(verpass)){
			SharedPreferences info2 = getSharedPreferences("datos",Context.MODE_PRIVATE);	
			Editor editor = info2.edit();
			editor.putString("password", newpass);
			editor.commit();
			}
		}
		
	}
	
	public void cancel(View view){
		finish();
	}
	

}
