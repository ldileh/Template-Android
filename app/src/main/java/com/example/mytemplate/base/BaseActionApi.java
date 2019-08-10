package com.example.mytemplate.base;

import android.content.Context;

import com.example.mytemplate.utils.UserMessage;

import retrofit2.Response;

public class BaseActionApi {

    private Context context;

    public BaseActionApi(Context context){
        this.context = context;
    }

    protected boolean checkResponseIsSuccess(Response response){
        if(response.code() == 200) return true;

        if(response.code() == 401 && context != null)
            UserMessage.defaultMessage(context, "Problem with authentication!");

        if(response.code() >= 500 && context != null)
            UserMessage.defaultMessage(context, "Something went wrong in server!");

        if(response.body() == null && context != null)
            UserMessage.defaultMessage(context, "Response is empty");

        return false;
    }
}
