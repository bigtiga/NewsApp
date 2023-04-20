package com.example.mynewsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomView extends RecyclerView.ViewHolder {
     TextView title_text,source_text;
     ImageView imgsrc;
     CardView cardView;

    public CustomView(@NonNull View itemView) {
        super(itemView);
        title_text= itemView.findViewById(R.id.title_text);
        source_text = itemView.findViewById(R.id.source_text);
        imgsrc = itemView.findViewById(R.id.imgsrc);
        cardView = itemView.findViewById(R.id.cardViewContainer);
    }
}
