package com.example.mytemplate.actionView;

import androidx.annotation.Nullable;

import retrofit2.Response;

public interface ResponseApiDefault<T> {

    void successApi(Response<T> response);

    void failedApi(@Nullable Response<T> response, String message);
}
