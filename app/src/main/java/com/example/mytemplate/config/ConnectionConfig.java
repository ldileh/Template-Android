package com.example.mytemplate.config;

import com.example.mytemplate.api.webRoot.GlobalEndpoint;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionConfig {

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

    public static GlobalEndpoint clientService(){
        return getRetrofitBuilder(GlobalConfig.getBaseUrl())
                .create(GlobalEndpoint.class);
    }

    public static GlobalEndpoint clientService(String baseUrl){
        return getRetrofitBuilder(baseUrl)
                .create(GlobalEndpoint.class);
    }

    public static GlobalEndpoint clientService(Class className){
        return (GlobalEndpoint) getRetrofitBuilder(GlobalConfig.getBaseUrl())
                .create(className);
    }
}
