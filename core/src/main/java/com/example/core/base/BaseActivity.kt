package com.example.core.base

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding>(bindingFactory: (LayoutInflater) -> T): BaseActivityVM<T, BaseViewModel>(bindingFactory)