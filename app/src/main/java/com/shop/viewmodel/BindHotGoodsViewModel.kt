package com.shop.viewmodel

import android.graphics.Color
import android.graphics.Color.red
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.shop.R
import com.shop.base.BaseViewModel
import com.shop.bean.HotgoodsData
import com.shop.net.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class   BindHotGoodsViewModel:BaseViewModel(Injection.repository) {
         //观察者模式
    var goods1 : MutableLiveData<HotgoodsData> = MutableLiveData()

    fun getGoodList(map:HashMap<String,String>){
        viewModelScope.launch {
            var result = repository.getGoodList(map)
            if (result!=null){
                goods1.postValue(result.data)
            }
        }
    }
}