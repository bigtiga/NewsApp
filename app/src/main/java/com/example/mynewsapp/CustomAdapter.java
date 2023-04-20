package com.example.mynewsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewsapp.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomView>{
    private Context context;
    private List<NewsHeadlines> headlinesList;
    private SelectListener listener_two;

    public CustomAdapter(Context context, List<NewsHeadlines> headlinesList,SelectListener listener_two) {
        this.context = context;
        this.headlinesList = headlinesList;
        this.listener_two = listener_two;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomView(LayoutInflater.from(context).inflate(R.layout.headline_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, @SuppressLint("RecyclerView") int position) {
        holder.title_text.setText(headlinesList.get(position).getTitle());
        holder.source_text.setText(headlinesList.get(position).getSource().getName());
        if(headlinesList.get(position).getUrlToImage() != null){
            Picasso.get()
                    .load(headlinesList.get(position).getUrlToImage())
                    .into(holder.imgsrc);
        }
        holder.getAdapterPosition();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener_two.onNewsClick(headlinesList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return headlinesList.size();
    }
}
