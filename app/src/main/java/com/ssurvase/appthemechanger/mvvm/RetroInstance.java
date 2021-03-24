package com.ssurvase.appthemechanger.mvvm;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    public static String BASEURL = "https://jsonplaceholder.typicode.com";
    private static Retrofit retrofit;
    public static Retrofit getRetrofitClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
