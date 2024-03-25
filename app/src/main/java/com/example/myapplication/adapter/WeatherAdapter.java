package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemWeatherBinding;
import com.example.myapplication.model.WeatherData;
import com.example.myapplication.model.WeatherForecast;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    Context context;
    ArrayList<WeatherData> weatherForecastArrayList;

    public WeatherAdapter(Context context, ArrayList<WeatherData> weatherForecastArrayList) {
        this.context = context;
        this.weatherForecastArrayList = weatherForecastArrayList;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWeatherBinding itemWeatherBinding =ItemWeatherBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(itemWeatherBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        WeatherData weatherData =weatherForecastArrayList.get(position);

        holder.itemWeatherBinding.temperature.setText(String.valueOf(weatherData.getMain().getTemp())+" ° C");
        holder.itemWeatherBinding.tvHumidity.setText(String.valueOf(weatherData.getMain().getHumidity())+ "%");
        holder.itemWeatherBinding.tvWind.setText(String.valueOf(weatherData.getWind().getSpeed())+ "m/s");
        holder.itemWeatherBinding.tvTpMin.setText(String.valueOf(weatherData.getMain().getTemp_min())+ " ° C");
        holder.itemWeatherBinding.tvTpMax.setText(String.valueOf(weatherData.getMain().getTemp_max())+ " ° C");
        holder.itemWeatherBinding.tvCity.setText("Hanoi, "+weatherData.getDt_txt());

        String weather_state = weatherData.getWeather().get(0).getMain();
        if(weather_state.equals("Clouds")){
            holder.itemWeatherBinding.imvWeatherIcon.setImageResource(R.drawable.weather_clounds);
        } else if (weather_state.equals("Rain")) {
            holder.itemWeatherBinding.imvWeatherIcon.setImageResource(R.drawable.rain);
        }else{
            holder.itemWeatherBinding.imvWeatherIcon.setImageResource(R.drawable.weather_sun_1);
        }

    }

    @Override
    public int getItemCount() {
        return weatherForecastArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemWeatherBinding itemWeatherBinding;
        public ViewHolder(@NonNull ItemWeatherBinding itemWeatherBinding) {
            super(itemWeatherBinding.getRoot());
            this.itemWeatherBinding = itemWeatherBinding;
        }
    }
}
