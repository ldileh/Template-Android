package com.example.core.base

import android.view.View
import androidx.viewbinding.ViewBinding

@Suppress("unused")
abstract class BaseFragment<T: ViewBinding>(bindingFactory: (View) -> T): BaseFragmentVM<T, BaseViewModel>(bindingFactory)