package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.WeatherData;
import com.example.myapplication.model.WeatherForecast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Weather.class);
            startActivity(intent);
        });
        Log.d("check_response", "onResponse: Oke calllllllll" );

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<WeatherForecast> call = apiService.callApiWeather("Hanoi","da9ec39cf80d21876a4c4d977a7a0cde","metric");
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                if(response.isSuccessful()){
                    WeatherData weatherData = response.body().getList().get(0);
                    Log.d("check_response", "onResponse: Oke " + weatherData.getMain().getTemp());
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

    private void initView(WeatherData weatherData) {
        activityMainBinding.temperature.setText(String.valueOf(weatherData.getMain().getTemp())+" ° C");
        activityMainBinding.tvHumidity.setText(String.valueOf(weatherData.getMain().getHumidity())+ "%");
        activityMainBinding.tvWind.setText(String.valueOf(weatherData.getWind().getSpeed())+ "m/s");
        activityMainBinding.tvTpMin.setText(String.valueOf(weatherData.getMain().getTemp_min())+ " ° C");
        activityMainBinding.tvTpMax.setText(String.valueOf(weatherData.getMain().getTemp_max())+ " ° C");
        activityMainBinding.tvCity.setText("Hanoi, "+weatherData.getDt_txt());

        String weather_state = weatherData.getWeather().get(0).getMain();
        if(weather_state.equals("Clouds")){
            activityMainBinding.imvWeatherIcon.setImageResource(R.drawable.weather_clounds);
        } else if (weather_state.equals("Rain")) {
            activityMainBinding.imvWeatherIcon.setImageResource(R.drawable.rain);
        }else{
            activityMainBinding.imvWeatherIcon.setImageResource(R.drawable.weather_sun_1);
        }

    }
}