package com.shop.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.shop.base.BaseViewModel
import com.shop.bean.DataX
import com.shop.net.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class BindBrandViewModel:BaseViewModel(Injection.repository) {

    var dataX :MutableLiveData<List<DataX>> = MutableLiveData(listOf())


    fun getBrand() {
        viewModelScope.launch {
            var result = repository.getBrand()
            if(result.errno == 0){
                dataX.postValue(result.data.data)
            }
        }

    }
}