package com.ssurvase.appthemechanger;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.google.gson.JsonElement;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepo {

    public MutableLiveData<List<PojoModel>> getPostData() {
        final MutableLiveData<List<PojoModel>> postModel = new MutableLiveData<>();
        ApiInterface apiInterface = new RetroClient().getInstance().create(ApiInterface.class);
//        "e898b2e3fa1338da"
        apiInterface.getLeaderboardOffline().enqueue(new Callback<List<PojoModel>>() {
            @Override
            public void onResponse(Call<List<PojoModel>> call, Response<List<PojoModel>> response) {
                Log.d("TAG", "onResponse = " + response);
                postModel.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PojoModel>> call, Throwable t) {
                Log.d("TAG", "FAILURE = " + t.getMessage());

            }
        });

        return postModel;

    }
}