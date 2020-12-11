package com.vingcoz.dentalapp.models;


import com.google.gson.annotations.SerializedName;

public class DoctorItem{

	@SerializedName("D_Specialization")
	private String dSpecialization;

	@SerializedName("D_Time")
	private String dTime;

	@SerializedName("D_Id")
	private int dId;

	@SerializedName("D_Name")
	private String dName;

	public void setDSpecialization(String dSpecialization){
		this.dSpecialization = dSpecialization;
	}

	public String getDSpecialization(){
		return dSpecialization;
	}

	public void setDTime(String dTime){
		this.dTime = dTime;
	}

	public String getDTime(){
		return dTime;
	}

	public void setDId(int dId){
		this.dId = dId;
	}

	public int getDId(){
		return dId;
	}

	public void setDName(String dName){
		this.dName = dName;
	}

	public String getDName(){
		return dName;
	}

	@Override
 	public String toString(){
		return 
			"DoctorItem{" + 
			"d_Specialization = '" + dSpecialization + '\'' + 
			",d_Time = '" + dTime + '\'' + 
			",d_Id = '" + dId + '\'' + 
			",d_Name = '" + dName + '\'' + 
			"}";
		}
}