package com.cc.couplecare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends Activity {
	// Splash screen time
	public static int SPLASH_TIME_OUT = 4000;
	public static String INITIALIZED = "initialized";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"datos", Context.MODE_PRIVATE);

		boolean initialized = pref.getBoolean(INITIALIZED, false);
		if (initialized == false) {

			animacion();
			SharedPreferences.Editor editor = pref.edit();
			editor.putBoolean(INITIALIZED, true);
			editor.commit();

			new Handler().postDelayed(new Runnable() {
				/*
				 * Mostrando la pantalla del splash con timer. Esto puede ser
				 * usable cuando tu quieras mostrar el logo de tu aplicación.
				 */

				@Override
				public void run() {

					// Cerrar esta actividad.
					finish();

				}
			}, SPLASH_TIME_OUT);

		}else{
			callsignup();
		}

	}

	// Metodo para la animacion del splash
	public void animacion() {
		ImageView img1 = (ImageView) findViewById(R.id.imgLogo);

		Animation fadein1 = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		img1.startAnimation(fadein1);

	}
	
	public void callsignup(){
		Intent i = new Intent(this, WelcomeCC.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		
	}

}
