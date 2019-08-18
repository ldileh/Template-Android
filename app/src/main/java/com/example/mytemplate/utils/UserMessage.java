package com.example.mytemplate.utils;

import android.content.Context;
import android.widget.Toast;

public class UserMessage {

    public static void defaultMessage(Context context, String message){
        if(context == null) return;

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
