package com.ssurvase.appthemechanger.mvvm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("/posts")
    Call<List<MoviewModel>> getListData();
}
