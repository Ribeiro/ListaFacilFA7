package br.com.androidos.listafacil.util;

import android.annotation.SuppressLint;
import android.widget.EditText;

public final class ViewUtil {
	
	private static final Integer ZERO = 0;
	
	private ViewUtil(){
		
	}
	
	public static String getStringFrom(EditText editText){
		return editText.getText().toString();
	}
	
	@SuppressLint("NewApi")
	public static Integer getIntegerFrom(EditText editText){
		String stringValueFromEditText = getStringFrom(editText);
		return stringValueFromEditText.isEmpty() ? ZERO : Integer.valueOf(stringValueFromEditText);
	}

	public static Double getDoubleFrom(EditText editText){
		String stringValueFromEditText = getStringFrom(editText);
		return stringValueFromEditText.isEmpty() ? ZERO : Double.valueOf(stringValueFromEditText);
		
	}
}
