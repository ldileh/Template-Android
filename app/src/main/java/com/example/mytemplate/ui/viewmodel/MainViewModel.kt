package com.example.mytemplate.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.core.utils.Resource
import com.example.mytemplate.domain.local.model.DefaultItemList
import com.example.mytemplate.domain.usecase.MainUseCase
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

            when(result){
                is Resource.Success -> {
                    userRepoResult.postValue(result.data?.map { DefaultItemList(it.id, it.name) } ?: mutableListOf())
                }

                is Resource.Failure -> {
                    // do default action in here.
                    // just remove this method if didn't need
                    onError(result.error)

                    // do some action failure in here.
                }
            }
        }
    }
}