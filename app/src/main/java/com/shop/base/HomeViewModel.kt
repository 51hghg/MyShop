package com.shop.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.shop.bean.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class HomeViewModel:ViewModel() {

    // 定义轮播图数据对象
    var banner:MutableLiveData<List<Banner>> = MutableLiveData()

    // 定义一个品牌制造商直供
    var brend:MutableLiveData<List<Brand>> = MutableLiveData()

    // 定义一个热门数据
    var hotGoods:MutableLiveData<List<HotGoods>> = MutableLiveData()

    // 定义一个热门数据
    var newGoods:MutableLiveData<List<NewGoods>> = MutableLiveData()

    //网络请求的状态值  -1 网络请求错误
    var loadStatue:MutableLiveData<Int> = MutableLiveData()

    /**
     * 加载首页数据
     */
    fun loadHomeData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData(){
        var homeData = get("https://cdplay.cn/api/index")
        if(homeData != null){
            banner.postValue(homeData.banner)
            brend.postValue(homeData.brandList)
            hotGoods.postValue(homeData.hotGoodsList)
        }else{
            loadStatue.postValue(-1)
        }
    }

    /**
     * 网络请求
     */
    suspend fun get(str:String) = withContext(Dispatchers.IO){
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson<HomeData>(result,HomeData::class.java)
    }


}