package com.ssurvase.appthemechanger.mvvm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<MoviewModel>> movieList;

    public MovieListViewModel() {
        movieList = new MutableLiveData<>();
    }

    public MutableLiveData<List<MoviewModel>> getMoviewListObserver(){
        return movieList;
    }

    public void makeAPiCall(){
        APIService apiService = RetroInstance.getRetrofitClient().create(APIService.class);
        Call<List<MoviewModel>> call = apiService.getListData();
        call.enqueue(new Callback<List<MoviewModel>>() {
            @Override
            public void onResponse(Call<List<MoviewModel>> call, Response<List<MoviewModel>> response) {
                movieList.postValue(response.body());
                Log.d("TAG", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<MoviewModel>> call, Throwable t) {
                movieList.postValue(null);
            }
        });

    }
}
