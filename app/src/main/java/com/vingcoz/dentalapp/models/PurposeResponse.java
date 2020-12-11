package com.vingcoz.dentalapp.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PurposeResponse{

	@SerializedName("purpose")
	private List<PurposeItem> purpose;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setPurpose(List<PurposeItem> purpose){
		this.purpose = purpose;
	}

	public List<PurposeItem> getPurpose(){
		return purpose;
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
			"PurposeResponse{" + 
			"purpose = '" + purpose + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}