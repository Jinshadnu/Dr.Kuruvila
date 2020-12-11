package com.vingcoz.dentalapp.models;

import com.google.gson.annotations.SerializedName;


public class PurposeItem{

	@SerializedName("Pu_Id")
	private int puId;

	@SerializedName("Pu_Name")
	private String puName;

	public void setPuId(int puId){
		this.puId = puId;
	}

	public int getPuId(){
		return puId;
	}

	public void setPuName(String puName){
		this.puName = puName;
	}

	public String getPuName(){
		return puName;
	}

	@Override
 	public String toString(){
		return 
			"PurposeItem{" + 
			"pu_Id = '" + puId + '\'' + 
			",pu_Name = '" + puName + '\'' + 
			"}";
		}
}