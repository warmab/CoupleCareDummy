package com.cc.couplecare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends Activity {
	//Splash screen time
 public static int SPLASH_TIME_OUT = 4000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		animacion();
		
		new Handler().postDelayed(new Runnable() {
			/*
			 * Mostrando la pantalla del splash con timer. 
			 * Esto puede ser usable cuando tu quieras mostrar el logo de tu aplicación.*/
			@Override
			public void run() {
			
				Intent i = new Intent(Splash.this, WelcomeCC.class);
				startActivity(i);
				
				//Cerrar esta actividad.
				finish();
				
			}
		},SPLASH_TIME_OUT);
	}
	
	//Metodo para la animacion del splash
	public void animacion(){
		ImageView img1 = (ImageView) findViewById(R.id.imgLogo);
		Animation fadein1 = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		img1.startAnimation(fadein1);
	}
	
	

}
