package com.cc.couplecare;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	@SuppressLint("NewApi")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);

	}

	@SuppressLint("NewApi")
	public void onDateSet(DatePicker view, int year, int month, int day) {
		String dateStart = day + "/" + month + "/" + year;
		SharedPreferences cycledate = getActivity().getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = cycledate.edit();
		editor.putInt("year", year);
		editor.putInt("month", month);
		editor.putInt("day", day);
		editor.putString("datestart", dateStart);
		editor.commit();
		
	}
	
	

}
