package objects;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Preferences extends Activity{
	private String email;
	private String pass;
	private String emailh;
	private String passh;
	private String syncPass;
	private Date startDate;
	private int duration;
	private boolean isLogged;	
	
	private String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	private String getPass() {
		return pass;
	}
	private void setPass(String pass) {
		this.pass = pass;
	}
	private String getEmailh() {
		return emailh;
	}
	private void setEmailh(String emailh) {
		this.emailh = emailh;
	}
	private String getPassh() {
		return passh;
	}
	private void setPassh(String passh) {
		this.passh = passh;
	}
	private String getSyncPass() {
		return syncPass;
	}
	private void setSyncPass(String syncPass) {
		this.syncPass = syncPass;
	}
	private Date getStartDate() {
		return startDate;
	}
	private void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	private int getDuration() {
		return duration;
	}
	private void setDuration(int duration) {
		this.duration = duration;
	}
	private boolean isLogged() {
		return isLogged;
	}
	private void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

}
