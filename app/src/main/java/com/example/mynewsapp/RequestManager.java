package com.example.mynewsapp;

import android.content.Context;
import android.widget.Toast;

import com.example.mynewsapp.Models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getNews_headlines(OnFetchDataListener listener,String category,String query){
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse>responseCall = callNewsApi.call_headlines("us",category,query, context.getString(R.string.apiKey));

        try{
            responseCall.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
                    }
                    assert response.body() != null;
                    listener.onFetchData(response.body().getArticles(),response.message());

                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("REQUEST FAILED");

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public RequestManager(Context context) {
        this.context = context;
    }

    public interface CallNewsApi{
        @GET("top-headlines")
        Call<NewsApiResponse> call_headlines(
                @Query("country")String country,
                @Query("category")String category,
                @Query("q")String query,
                @Query("apiKey")String api_key
        );
    }
}
