package com.cc.couplecare;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class ListFertilDays extends Activity {
	Button btnlista;
	Button btnDiasFertile;
	Button btnperiodo;
	ListView lv1;
	Calendar dia;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listfertildays);
		btnlista = (Button)findViewById(R.id.btnlist);
		btnDiasFertile = (Button)findViewById(R.id.btncalcular);
		btnperiodo = (Button)findViewById(R.id.btnper);
	    lv1 = (ListView)findViewById(R.id.listView1);
	    
	    
	   }
	
	/*public void diasfertiles(){
		 Day dia = new Day();
		 dia.calcuFertilPeriod();
		 dia.formatoListo();
	}*/



}
