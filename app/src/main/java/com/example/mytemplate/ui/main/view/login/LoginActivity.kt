package com.example.mytemplate.ui.main.view.login

import android.os.Bundle
import com.example.mytemplate.base.BaseActivity
import com.example.mytemplate.databinding.ActivityLoginBinding
import com.example.mytemplate.ui.main.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(ActivityLoginBinding::inflate) {

    override fun toolbarId(): Int? = null

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

    override fun ActivityLoginBinding.initViewBinding() {

    }
}