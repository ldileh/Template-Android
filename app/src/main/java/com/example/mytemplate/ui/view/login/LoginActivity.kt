package com.example.mytemplate.ui.view.login

import android.os.Bundle
import com.example.core.base.BaseActivity
import com.example.core.base.BaseViewModel
import com.example.mytemplate.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun toolbarId(): Int? = null

    override fun ActivityLoginBinding.onViewCreated(savedInstanceState: Bundle?) {

    }
}