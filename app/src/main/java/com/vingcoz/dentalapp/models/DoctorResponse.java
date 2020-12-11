package com.vingcoz.dentalapp.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class DoctorResponse{

	@SerializedName("doctor")
	private List<DoctorItem> doctor;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setDoctor(List<DoctorItem> doctor){
		this.doctor = doctor;
	}

	public List<DoctorItem> getDoctor(){
		return doctor;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"DoctorResponse{" + 
			"doctor = '" + doctor + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}