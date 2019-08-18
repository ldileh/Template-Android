package com.example.mytemplate.api;

import android.content.Context;

import com.example.mytemplate.actionView.ResponseApiDefault;
import com.example.mytemplate.base.BaseActionApi;
import com.example.mytemplate.config.RetrofitConfig;
import com.example.mytemplate.main.model.api.GithubRepoResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class GlobalApi extends BaseActionApi {

    public GlobalApi(Context context){
        super(context);
    }

    public void getUserRepo(ResponseApiDefault<List<GithubRepoResponseModel>> callback, String username){
        RetrofitConfig.clientService()
                .example(username)
                .enqueue(new Callback<List<GithubRepoResponseModel>>() {
                    @EverythingIsNonNull
                    @Override
                    public void onResponse(Call<List<GithubRepoResponseModel>> call, Response<List<GithubRepoResponseModel>> response) {
                        if(checkResponseIsSuccess(response))
                            callback.successApi(response);
                        else
                            callback.failedApi(response, response.message());
                    }

                    @EverythingIsNonNull
                    @Override
                    public void onFailure(Call<List<GithubRepoResponseModel>> call, Throwable t) {
                        callback.failedApi(null, t.getMessage());
                    }
                });
    }
}
