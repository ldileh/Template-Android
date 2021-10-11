package com.example.mytemplate.ui.view.login

import android.os.Bundle
import com.example.core.base.BaseActivity
import com.example.mytemplate.databinding.ActivityLoginBinding
import com.example.mytemplate.ui.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(ActivityLoginBinding::inflate) {

    override fun toolbarId(): Int? = null

    override fun ActivityLoginBinding.onViewCreated(savedInstanceState: Bundle?) {

    }
}