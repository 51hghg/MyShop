package com.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.shop.bean.Brand
import com.shop.bean.HomeData
import com.shop.bean.HotGoods
import com.shop.bean.NewGoods

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class BindHomeViewModel:ViewModel() {

    var brand:MutableLiveData<List<Brand>> = MutableLiveData(listOf())

    var newGoods:MutableLiveData<List<NewGoods>> = MutableLiveData(listOf())

    var hotGoods:MutableLiveData<List<HotGoods>> = MutableLiveData(listOf())

    fun homeData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData(){
        var homeData = get("https://cdplay.cn/api/index")
        if(homeData != null){
            brand.postValue(homeData.brandList)
            newGoods.postValue(homeData.newGoodsList)
            hotGoods.postValue(homeData.hotGoodsList)

        }else{
        }
    }

    /**
     * 网络请求
     */
    suspend fun get(str:String) = withContext(Dispatchers.IO){
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson<HomeData>(result, HomeData::class.java)
    }

}