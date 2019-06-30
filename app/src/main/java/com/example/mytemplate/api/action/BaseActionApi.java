package com.example.mytemplate.api.action;

import android.content.Context;

import com.example.mytemplate.utils.UserMessage;

import retrofit2.Response;

class BaseActionApi {

    private Context context;

    BaseActionApi(){}

    BaseActionApi(Context context){
        this.context = context;
    }

    boolean checkResponseIsSuccess(Response response){
        if(response.code() == 200) return true;

        if(response.code() == 401 && context != null)
            UserMessage.defaultMessage(context, "Problem with authentication!");

        if(response.code() >= 500 && context != null)
            UserMessage.defaultMessage(context, "Something went wrong in server!");

        return false;
    }
}
