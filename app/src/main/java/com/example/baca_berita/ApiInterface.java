package com.example.baca_berita;

import com.example.baca_berita.Model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<Headlines> getHeadLines (

        @Query("country") String country,
                @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<Headlines> getSpecifiedData (

            @Query("q") String query,
            @Query("apiKey") String apiKey
    );
}