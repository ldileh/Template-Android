package com.example.mytemplate.config;

import com.example.mytemplate.api.GlobalApiServices;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final static String baseUrl = GlobalConfig.getBaseUrlExample();

    private static Retrofit getRetrofitBuilder(String baseUrl){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        return builder
                .client(httpClient.build())
                .build();
    }

    public static GlobalApiServices clientService(){
        return getRetrofitBuilder(baseUrl)
                .create(GlobalApiServices.class);
    }

    public static GlobalApiServices clientService(String baseUrl){
        return getRetrofitBuilder(baseUrl)
                .create(GlobalApiServices.class);
    }
}
