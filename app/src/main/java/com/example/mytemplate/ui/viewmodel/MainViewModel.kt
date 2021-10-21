package com.example.mytemplate.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.mytemplate.data.local.model.DefaultItemList
import com.example.mytemplate.data.usecase.MainUseCase
import com.example.core.utils.ResultRepository.Status.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainUseCase
) : BaseViewModel() {

    val eventLoadUserRepo = MutableLiveData<Boolean>()
    val userRepoResult = MutableLiveData<List<DefaultItemList>>()

    fun getUserRepo(username: String) = launch {
        eventMessage.postValue("Test, hello!")
        eventLoadUserRepo.postValue(true)

        repository.callUsers(username).getResultCase { result ->
            eventLoadUserRepo.postValue(false)

            when(result.status){
                SUCCESS -> userRepoResult.postValue(result.data?.map { DefaultItemList(it.id, it.name) } ?: mutableListOf())

                FAILED -> onError(result.message)

                LOADING -> { }
            }
        }
    }
}