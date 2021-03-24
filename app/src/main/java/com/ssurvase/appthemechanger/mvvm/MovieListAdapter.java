package com.ssurvase.appthemechanger.mvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ssurvase.appthemechanger.R;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private Context context;
    private List<MoviewModel> listData;


    public MovieListAdapter(Context context, List<MoviewModel> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void setmovieList(List<MoviewModel> listData){
        this.listData = listData;
        notifyDataSetChanged();
     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.mvvm_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(listData.get(position).getId() + " || "+ listData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(listData!=null){
            return listData.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}
