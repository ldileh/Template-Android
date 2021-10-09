package com.example.mytemplate.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytemplate.utils.ResultRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel(), CoroutineScope{

    private val supervisorJob = SupervisorJob()

    override val coroutineContext: CoroutineContext get() = dispatcher + supervisorJob

    val eventMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun onError(msg: String?, code: Int? = null) = when(code){
        401 -> { // token expired event
            eventMessage.postValue(msg?:"")

            // handle action when token expired in here
        }

        else -> eventMessage.postValue(msg?:"")
    }

    suspend inline fun <T> ResultRepository<T>.getResultCase(crossinline callback: (result: ResultRepository<T>) -> Unit){
        withContext(Dispatchers.Main){
            callback(this@getResultCase)
        }
    }
}