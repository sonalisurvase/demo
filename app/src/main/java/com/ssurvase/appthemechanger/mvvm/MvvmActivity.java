package com.ssurvase.appthemechanger.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.ssurvase.appthemechanger.R;
import java.util.List;

public class MvvmActivity extends AppCompatActivity {

    private List<MoviewModel> listData;
    MovieListAdapter movieListAdapter;
    MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);

        RecyclerView recyclerView = findViewById(R.id.movie_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        movieListAdapter = new MovieListAdapter(this, listData);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieListAdapter);
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.makeAPiCall();
        viewModel.getMoviewListObserver().observe(this, new Observer<List<MoviewModel>>() {
            @Override
            public void onChanged(List<MoviewModel> moviewModels) {
                if (moviewModels != null){
                    listData = moviewModels;
                    movieListAdapter.setmovieList(listData);
                    movieListAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(movieListAdapter);
                }
            }
        });


    }
}