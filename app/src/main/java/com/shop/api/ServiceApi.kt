package com.shop.api

import com.shop.bean.*
import com.shop.tongpao.HotData
import com.shop.net.BaseResp
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.HashMap

interface ServiceApi {


    @POST("auth/refreshToken")  //刷新token
    suspend fun refreshToken(): BaseResp<String>

    @GET("index")
    suspend fun getHome():BaseResp<HomeData>   // BaseResp抽取的一个bean类的外层结构 homeData当前接口返回的具体

    //品牌制造
    @GET("brand/list")
    suspend fun getBrand():BaseResp<BrandData>

    //专题
    @GET("topic/list?page=1&size=10")
    suspend fun getTopic():BaseResp<TopicData>


    //多布局的接口
    @GET("discover/hot.json")
    suspend fun getMore(): HotData

    //商品列表详情
    @GET("goods/list")
    suspend fun getGoodList(@QueryMap map: HashMap<String, String>):BaseResp<HotgoodsData>

    @GET("catalog/index")
    suspend fun getSort():BaseResp<SortData>

    @GET("catalog/current")
    suspend fun getSortList(@Query("id") id: String):BaseResp<SortListData>

    @GET("goods/list")
    suspend fun getGoodList1(@Query("id") id: String):BaseResp<GoodListData>
}