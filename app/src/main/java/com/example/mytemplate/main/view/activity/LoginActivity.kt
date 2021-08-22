package com.example.mytemplate.main.view.activity

import android.os.Bundle
import com.example.mytemplate.base.BaseActivity
import com.example.mytemplate.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun toolbarId(): Int? = null

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }
}