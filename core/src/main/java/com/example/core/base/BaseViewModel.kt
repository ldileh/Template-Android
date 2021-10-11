package com.example.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.utils.ResultRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel(), CoroutineScope{

    private val supervisorJob = SupervisorJob()

    override val coroutineContext: CoroutineContext get() = dispatcher + supervisorJob

    val eventMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val eventRestart: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun onError(msg: String?){
        eventMessage.postValue(msg?:"")
    }

    /**
     * Handle response from remote data.
     * In this case, handle response code token expired to inform ui
     */
    suspend inline fun <T> ResultRepository<T>.getResultCase(crossinline callback: (result: ResultRepository<T>) -> Unit){
        withContext(Dispatchers.Main){
            if (code != null)
                // check if response code is token expired
                if (code == 401)
                    eventRestart.postValue(true)
                else
                    callback(this@getResultCase)
            else
                callback(this@getResultCase)
        }
    }
}