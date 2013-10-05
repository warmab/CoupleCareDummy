package utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Validations extends Activity {
	public boolean isDataSaved() {
		return false;
	}

	public boolean isManDataSaved() {
		return false;
	}

	public boolean isCycleDataSaved() {
		return false;
	}

	public boolean isLoggedIn() {
		return false;
	}

	/*
	 * public boolean isOnline(Context ctx){ ConnectivityManager cm =
	 * (ConnectivityManager)
	 * this.getSystemService(Context.CONNECTIVITY_SERVICE); // No puede estar
	 * dentro de la clase por necesita instanciarse en la actividad NetworkInfo
	 * netInfo = cm.getActiveNetworkInfo(); if (netInfo != null &&
	 * netInfo.isConnected()) { return true; } return false; }
	 */

	public String isChuleta() {
		return "Si a huevo, se te descongela :P";
	}

}
