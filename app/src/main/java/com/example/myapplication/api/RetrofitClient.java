package com.example.myapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
//    private static RetrofitClient instance;
//    private static Retrofit retrofit;
//
//
//
//
//    private RetrofitClient(String domain) {
//        Interceptor interceptor = chain -> {
//            Request request = chain.request();
//            Request.Builder builder = request.newBuilder();
//            return chain.proceed(builder.build());
//        };
//        OkHttpClient.Builder okBuilder =
//                new OkHttpClient.Builder()
//                        .addInterceptor(interceptor)
//                        .callTimeout(200000, TimeUnit.MILLISECONDS) // Timeout cho toàn bộ cuộc gọi (bao gồm cả kết nối và đọc/phản hồi)
//                        .connectTimeout(200000, TimeUnit.MILLISECONDS)
//                        .readTimeout(200000, TimeUnit.MILLISECONDS);
//
//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                .create();
//        retrofit = new Retrofit.Builder()
//                .baseUrl(domain)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//                .client(okBuilder.build())
//                .build();
//    }
//    public static synchronized RetrofitClient getInstance(String domain) {
//        if (instance == null) {
//            instance = new RetrofitClient(domain);
//        }
//        return instance;
//    }
//
//    // Get the Retrofit instance
//    public Retrofit getRetrofit() {
//        return retrofit;
//    }
}
