package com.example.mynewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynewsapp.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class Details_Activity extends AppCompatActivity {
    NewsHeadlines headlines_;
    TextView text_title_,details_author,time_detail,text_details_detail,text_content;
    ImageView img_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        text_title_ = findViewById(R.id.text_title_);
        details_author = findViewById(R.id.details_author);
        time_detail = findViewById(R.id.time_detail);
        text_details_detail = findViewById(R.id.text_details_detail);
        text_content = findViewById(R.id.text_content);
        img_detail = findViewById(R.id.img_detail);

        headlines_ = (NewsHeadlines) getIntent().getSerializableExtra("data");

        text_title_.setText(headlines_.getTitle());
        details_author.setText(headlines_.getAuthor());
        time_detail.setText(headlines_.getPublishedAt());
        text_details_detail.setText(headlines_.getDescription());
        text_content.setText(headlines_.getContent());
        Picasso.get().load(headlines_.getUrlToImage()).into(img_detail);

    }
}