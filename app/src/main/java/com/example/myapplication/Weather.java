package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.adapter.WeatherAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.databinding.ActivityWeatherBinding;
import com.example.myapplication.model.WeatherData;
import com.example.myapplication.model.WeatherForecast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Weather extends AppCompatActivity {

    WeatherAdapter weatherAdapter;
    ActivityWeatherBinding activityWeatherBinding;
    ArrayList<WeatherData> weatherData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWeatherBinding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(activityWeatherBinding.getRoot());

        weatherData = new ArrayList<>();
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<WeatherForecast> call = apiService.callApiWeather("Hanoi","da9ec39cf80d21876a4c4d977a7a0cde","metric");
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                if(response.isSuccessful()){
                    weatherData.addAll(response.body().getList());
                    Log.d("check_response", "onResponse: Oke "  );
                    initView(weatherData);
                }else {
                    Log.d("check_response", "onResponse: "+ response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Log.d("check_response", "onFailure: "+ t.getMessage());
            }
        });
    }

    private void initView(ArrayList<WeatherData> weatherData) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        activityWeatherBinding.rec.setLayoutManager(linearLayoutManager);
        weatherAdapter = new WeatherAdapter(this,weatherData);
        activityWeatherBinding.rec.setAdapter(weatherAdapter);
    }
}