package com.shop.net.repository

import com.shop.api.ServiceApi
import com.shop.net.RetrofitFactory

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 数据仓库
 * 用来连接ViewModel和Model
 * 定义业务相关的网络请求接口的api
 */
class SystemRepository {

    private lateinit var serviceApi: ServiceApi

    //初始化的方法
    init {
        serviceApi = RetrofitFactory.instance.create(ServiceApi::class.java)
    }

    /**
     * 刷新token
     */
    suspend fun refreshToken() = withContext(Dispatchers.IO){
        serviceApi.refreshToken()
    }


    /**
     * 获取主页数据
     */
    suspend fun getHome() = withContext(Dispatchers.IO){
        serviceApi.getHome()
    }

    //品牌制造商
    suspend fun getBrand() = withContext(Dispatchers.IO){
        serviceApi.getBrand()
    }
    /**
     * 获取专题数据
     */
    suspend fun getTopic() = withContext(Dispatchers.IO){
        serviceApi.getTopic()
    }

    suspend fun getMore() = withContext(Dispatchers.IO){
        serviceApi.getMore()
    }

    /**
     * 获取商品列表数据
     */
    suspend fun getGoodList(map:HashMap<String,String>) = withContext(Dispatchers.IO){
        serviceApi.getGoodList(map)
    }

    //竖导航
    suspend fun getSortData() = withContext(Dispatchers.IO){
        serviceApi.getSort()
    }

    //
    suspend fun getCatalogData(id:String) = withContext(Dispatchers.IO){
        serviceApi.getSortList(id)
    }

    suspend fun getGoodList1(id:String) = withContext(Dispatchers.IO){
        serviceApi.getGoodList1(id)
    }
}