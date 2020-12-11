/*
 * *
 *  * Created by Reynosh Sunny on 23/10/19 1:35 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 22/10/19 10:48 PM
 *
 */

package com.vingcoz.dentalapp.utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://drkuruvilamemorial.site/Main_api/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
