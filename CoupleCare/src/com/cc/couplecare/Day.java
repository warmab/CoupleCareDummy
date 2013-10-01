package com.cc.couplecare;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import objects.AnsPreferences;
import objects.Answer;
import objects.Preferences;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



public class Day{
	private Calendar day;
	private boolean fertile;
    public String comment;

    
    public String formatoListo(Calendar day){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(day.getTime());
    }
    
    
    
    public String getSharedDateStart(){
		 SharedPreferences settigns = new MysettingsActivity().getSharedPreferences("datacycle", Context.MODE_PRIVATE); 
		 int year = settigns.getInt("year", 0);
		 int month = settigns.getInt("month", 0);
		 int day = settigns.getInt("day", 0);
		 String date = day + " " + month + " " + year;
		 return date;  
	}
    
    public void setDateStart(int year, int month, int day){
    	SharedPreferences datecycle = new MysettingsActivity().getSharedPreferences("datacycle", Context.MODE_PRIVATE); 
    	Editor editor = datecycle.edit();
        editor.putInt("year", year);
        editor.putInt("month", month);
        editor.putInt("day", day);
        editor.commit();
    	}
     
    public Integer getDuration(){
    	SharedPreferences durationshar = new MysettingsActivity().getSharedPreferences("duration", Context.MODE_PRIVATE); 
		 int duration = durationshar.getInt("duration", 0);
		 return duration; 	
    }
    
    public Integer setDuration(int duration){
    	SharedPreferences durationshar = new MysettingsActivity().getSharedPreferences("duration", Context.MODE_PRIVATE); 
    	Editor editor = durationshar.edit();
        editor.putInt("duration", duration);
        return duration;
    }
    
    public Calendar calculmoreFertilDay(Calendar lastday, int cycleDurat){
    	cycleDurat = this.getDuration();
    	lastday.add(day.DAY_OF_MONTH, cycleDurat/2);
    	return lastday;
    	}
    
    public void calcuFertilPeriod(Calendar day){
    	String inicioFertil;
    	String finalFertil;
    	
    	//Calculate final of fertile period
    	day.add(day.DAY_OF_MONTH, -3);
    	inicioFertil = formatoListo(day);
    	
    	//Calculate start of period
    	day.add(Calendar.DAY_OF_MONTH, -3);
    	finalFertil = formatoListo(day);
    	}
    
    public ArrayList<Object> createList(){
    	ArrayList<Object> list = new ArrayList<Object>();
    	
    	day = this.getDay();
    	int dur = getDuration();
    	String[] dias = new String[dur];
    	for(int i=0; i<=dur; i++){
    		day.add(day.DAY_OF_MONTH, 1);
    		dias[i] = day.toString();
    		//list.add(day);
    		
    	}
    	list.add(dias);
    	return list;
    }
    
    public boolean isThereEmail() throws IOException{
    	boolean flag;
    	
    	try {
    		//aqui consulto la shared preferences
    		flag = true;
    	} catch (Exception nex){
    		flag = false;
    		//toast(nex.getMessage());
    	} finally {
    		flag = true;
    	}
    	
    	return flag;
    }
    
    public String getSharedEmail(){
    	boolean flag;
    	
    	return "";
    }
    
	public AnsPreferences consulta(){
		AnsPreferences ap = new AnsPreferences();
		Answer a = new Answer();
		Preferences p = new Preferences();
		boolean faltaAlgo = false;
		try {
		if (isThereEmail()){// && isTherePassword() && isThereChuleta()){
			
		} else {
			System.out.print("Como estas meco no hay una shared");
		}
		} catch (Exception meco){
			
		}
		
		try {
			int i = 0;
			//
		} catch (NullPointerException nex){
			faltaAlgo = true;
		}
		
		if (faltaAlgo)
			a.setIdAnswer(01);
		
		if (1 > 0){
			a.setIdAnswer(00);
			a.setMessage("Succes");
			p.setEmail("victorhgr89@gmail.com");
		} else {
			a.setIdAnswer(01);
			a.setMessage("Not bigger than 0");
		}
		
		ap.setAnswer(a);
		ap.setPreferences(p);
		
		return ap;
	}
	
	public void noMames(){
		AnsPreferences ansPref = new AnsPreferences();
		ansPref = consulta();
		if (ansPref.getAnswer().getIdAnswer() == 00){
			Preferences p = ansPref.getPreferences();
			//toast(p.getEmail());
			
		} else {
			//toast(ansPref.getAnswer().getMessage());
		}
	}
    
    
    public Calendar getDay(){
    	return day;
    }
    
    public void setDay(Calendar day){
    	this.day=day;
    }
    
    public boolean getfertile(){
    	return fertile;
    }
    
    public void setfertile(Boolean fertile){
    	this.fertile=fertile;
    }
    
    public String getcomment(){
    	return comment;
    }
    
    public void setcomment(String comment){
    	this.comment=comment;
    }
}
