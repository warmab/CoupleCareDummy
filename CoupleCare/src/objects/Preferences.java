package objects;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Preferences{
	
	private String email;
	private String pass;
	private String emailh;
	private String passh;
	private String syncPass;
	private Date startDate;
	private int duration;
	private boolean isLogged;	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmailh() {
		return emailh;
	}
	public void setEmailh(String emailh) {
		this.emailh = emailh;
	}
	public String getPassh() {
		return passh;
	}
	public void setPassh(String passh) {
		this.passh = passh;
	}
	public String getSyncPass() {
		return syncPass;
	}
	public void setSyncPass(String syncPass) {
		this.syncPass = syncPass;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	
	public String toString(){
		fechaMostrar("");
		return "";
	}
	/**
	 * Esta madre esta bien chida
	 * 
	 * @return 
	 * String bien chido
	 */
	public String fechaMostrar(String esputo){
		return "la fecha es " + startDate.toString() + " madafaka";
	}

}
