package com.ssurvase.appthemechanger;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PostViewModel extends ViewModel {
    public ApiRepo apiRepo;
    MutableLiveData<List<PojoModel>> getPost;

    public PostViewModel(){
         apiRepo = new ApiRepo();
    }

    public LiveData<List<PojoModel>> getPostData(){
        if (getPost == null){
            getPost = apiRepo.getPostData();
        }
        return getPost;
    }
}
