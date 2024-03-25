package com.example.myapplication.api;

import com.example.myapplication.model.WeatherForecast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

//    Gson gson = new GsonBuilder().create();
//
//
//    ApiService apiService = new Retrofit.Builder()
//            .baseUrl("http://api.openweathermap.org/data/2.5/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService.class);

    @GET("forecast")
    Call<WeatherForecast> callApiWeather(
        @Query("q") String city,
        @Query("appid") String appid,
        @Query("units") String unit
    );

}
