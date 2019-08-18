package com.example.mytemplate.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.example.mytemplate.main.model.api.*;

import java.util.List;

public interface GlobalApiServices {

    @GET("/users/{user}/repos")
    Call<List<GithubRepoResponseModel>> example(@Path("user") String user);

    @GET("/index")
    Call<Object> index();
}
