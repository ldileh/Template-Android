package com.example.mytemplate.main.view.activity.base;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    }
}
