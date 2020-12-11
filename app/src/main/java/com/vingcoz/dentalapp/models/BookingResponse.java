package com.vingcoz.dentalapp.models;


import com.google.gson.annotations.SerializedName;


public class BookingResponse{

	@SerializedName("booking_id")
	private int bookingId;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setBookingId(int bookingId){
		this.bookingId = bookingId;
	}

	public int getBookingId(){
		return bookingId;
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
			"BookingResponse{" + 
			"booking_id = '" + bookingId + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}