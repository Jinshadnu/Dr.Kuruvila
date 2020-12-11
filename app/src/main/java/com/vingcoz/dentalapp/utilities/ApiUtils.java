/*
 * *
 *  * Created by Reynosh Sunny on 23/10/19 1:35 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 23/10/19 1:15 AM
 *
 */

package com.vingcoz.dentalapp.utilities;


import com.vingcoz.dentalapp.models.BookingResponse;
import com.vingcoz.dentalapp.models.DoctorResponse;
import com.vingcoz.dentalapp.models.OtpResponse;
import com.vingcoz.dentalapp.models.OtpVerificationResponse;
import com.vingcoz.dentalapp.models.PurposeResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiUtils {


    @POST("GetDoctors")
    Call<DoctorResponse> GetDoctors();

    @POST("GetPurposes")
    Call<PurposeResponse> GetPurpose();

    @FormUrlEncoded
    //  @Headers({"Content-Type: application/x-www-form-urlencoded,token:Bearer"})
    @POST("NewBooking")
    Call<BookingResponse> RequestSignUp(@Field("PatientID") String PatientID,
                                        @Field("PatientName") String PatientName,
                                        @Field("Mobile") String Mobile,
                                        @Field("Address") String Address,
                                        @Field("Email") String Email,
                                        @Field("DoctorID") int DoctorID,
                                        @Field("PurposeID") int PurposeID,
                                        @Field("BookingDate") String BookingDate,
                                        @Field("BookingTime") String BookingTime,
                                        @Field("DeviceID") String DeviceID);


    @FormUrlEncoded
    @POST("VerifyMobileNumber")
    Call<OtpResponse> SendOTP(@Field("MobileNumber") String strMobileNumber);

    @FormUrlEncoded
    @POST("VerifyOtp")
    Call<OtpVerificationResponse> VerifyOtp(@Field("OtpId") long lngOtpID,
                                            @Field("OtpCode") String intOtpCode,
                                            @Field("MobileNumber") String strMobileNumber);



/*

    @FormUrlEncoded
  //  @Headers({"Content-Type: application/x-www-form-urlencoded,token:Bearer"})
    @POST("business_list")
    Call<ListResponse> RequestList(@Field("user_id") long UserID,
                                   @Field("business_category") int Category,
                                   @Field("keywords") String SearchWords,
                                   @Field("device_type") String DeviceType,
                                   @Field("device_token") String DeviceToken);
*/

}
