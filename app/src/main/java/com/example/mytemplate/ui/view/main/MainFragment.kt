package com.example.mytemplate.ui.view.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragmentVM
import com.example.mytemplate.databinding.FragmentMainBinding
import com.example.mytemplate.ui.viewmodel.MainViewModel

@Suppress("unused")
class MainFragment: BaseFragmentVM<FragmentMainBinding, MainViewModel>(FragmentMainBinding::bind) {

    override val viewModel: MainViewModel by viewModels()

    override fun FragmentMainBinding.onViewCreated(savedInstanceState: Bundle?) { }

    override fun observeViewModel(viewModel: MainViewModel) {
        super.observeViewModel(viewModel)
    }
}