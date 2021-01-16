package com.shop.ui.home.banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.shop.bean.Banner
import com.shop.bean.Brand
import com.shop.bean.HomeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Array.get
import java.net.URL

class BindBannerViewModel :ViewModel() {
    var banner:MutableLiveData<List<Banner>> = MutableLiveData(listOf())

    fun homeData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData() {
        var homeData = get("https://cdplay.cn/api/index")
        if(homeData != null){
            banner.postValue(homeData.banner)
        }
    }

    suspend fun get(str:String) = withContext(Dispatchers.IO){
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson<HomeData>(result, HomeData::class.java)
    }
}