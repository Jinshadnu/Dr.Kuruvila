package com.vingcoz.dentalapp.models;

import com.google.gson.annotations.SerializedName;


public class OtpResponse{

	@SerializedName("otpid")
	private int otpid;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setOtpid(int otpid){
		this.otpid = otpid;
	}

	public int getOtpid(){
		return otpid;
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
			"OtpResponse{" + 
			"otpid = '" + otpid + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}