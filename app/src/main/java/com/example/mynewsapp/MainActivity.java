
package com.example.mynewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mynewsapp.Models.NewsApiResponse;
import com.example.mynewsapp.Models.NewsHeadlines;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener{

    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ProgressDialog progressDialog;
    Button btn_one,btn_two,btn_three,btn_four,btn_five,btn_six,btn_seven;
    SearchView search_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view = findViewById(R.id.search_view);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching news article of "+ query);
                progressDialog.show();
                RequestManager requestManager = new RequestManager(MainActivity.this);
                requestManager.getNews_headlines(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btn_one = findViewById(R.id.btn_one);
        btn_one.setOnClickListener(MainActivity.this);
        btn_two = findViewById(R.id.btn_two);
        btn_two.setOnClickListener(MainActivity.this);
        btn_three = findViewById(R.id.btn_three);
        btn_three.setOnClickListener(MainActivity.this);
        btn_four = findViewById(R.id.btn_four);
        btn_four.setOnClickListener(MainActivity.this);
        btn_five = findViewById(R.id.btn_five);
        btn_five.setOnClickListener(MainActivity.this);
        btn_six = findViewById(R.id.btn_six);
        btn_six.setOnClickListener(MainActivity.this);
        btn_seven = findViewById(R.id.btn_seven);
        btn_seven.setOnClickListener(MainActivity.this);

        recyclerView = findViewById(R.id.recyclerviewone);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Fetching news articles..");
        progressDialog.show();

        RequestManager requestManager = new RequestManager(MainActivity.this);
        requestManager.getNews_headlines(listener,"general",null);
    }

    private final OnFetchDataListener<NewsApiResponse>listener = new OnFetchDataListener<NewsApiResponse>(){
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this,"No data found",Toast.LENGTH_LONG).show();
            }
            else {

                getNewsData(list);
                progressDialog.dismiss();
            }
        }

        private void getNewsData(List<NewsHeadlines> list_) {
            recyclerView = findViewById(R.id.recyclerviewone);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1, RecyclerView.VERTICAL,false));
            customAdapter = new CustomAdapter(MainActivity.this,list_,MainActivity.this);
            recyclerView.setAdapter(customAdapter);
           // recyclerView.invalidate();
            customAdapter.notifyDataSetChanged();
            Log.d("MainActivity", "Size of list: " + list_.size());
        }

        @Override
        public void onError(String message){
            Toast.makeText(MainActivity.this,"Error Occured!!",Toast.LENGTH_LONG).show();


        }
    };

    @Override
    public void onNewsClick(NewsHeadlines newsHeadlines) {
        Intent intent = new Intent(MainActivity.this,Details_Activity.class);
        intent.putExtra("data",newsHeadlines);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();
        progressDialog.setTitle("Fetching new article of " + category);
        RequestManager requestManager = new RequestManager(MainActivity.this);
        requestManager.getNews_headlines(listener,"category",null);

    }
}
