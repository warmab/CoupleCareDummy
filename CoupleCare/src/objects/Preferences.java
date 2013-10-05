package objects;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Preferences extends Activity {
	private String email;
	private String pass;
	private String emailh;
	private String passh;
	private String syncPass;
	private Date startDate;
	private int duration;
	private boolean isLogged;

	public void deletedShared() {
		SharedPreferences prefs = getSharedPreferences("datos", MODE_PRIVATE);

		SharedPreferences.Editor editor = prefs.edit();

		editor.clear();
		editor.commit();
	}

	private String getEmail() {
		SharedPreferences prefe = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		prefe.getString("mail", "");
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private String getPass() {
		SharedPreferences prefe = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		prefe.getString("password", "");
		return pass;
	}

	private void setPass(String pass) {
		this.pass = pass;
	}

	private String getEmailh() {
		SharedPreferences prefe = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		prefe.getString("emailmen", "");
		return emailh;
	}

	private void setEmailh(String emailh) {
		this.emailh = emailh;
	}

	private String getPassh() {
		SharedPreferences prefe = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		prefe.getString("passmen", "");
		return passh;
	}

	private void setPassh(String passh) {
		this.passh = passh;
	}

	private String getSyncPass() {
		SharedPreferences prefe = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		prefe.getString("passsync", "");
		return syncPass;
	}

	private void setSyncPass(String syncPass) {
		this.syncPass = syncPass;
	}

	private Date getStartDate() {
		SharedPreferences prefe = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		prefe.getString("datestart", "");
		return startDate;
	}

	private void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	private int getDuration() {
		SharedPreferences prefe = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		prefe.getString("durationcycle", "");
		return duration;
	}

	private void setDuration(int duration) {
		this.duration = duration;
	}

	private boolean isLogged() {
		SharedPreferences prefe = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		prefe.getBoolean("logged", false);
		return isLogged;
	}

	private void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

}
