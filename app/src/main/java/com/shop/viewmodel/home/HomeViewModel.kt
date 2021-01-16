package com.shop.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.base.BaseViewModel
import com.shop.bean.*
import com.shop.net.Injection
import kotlinx.coroutines.launch

class HomeViewModel:BaseViewModel(Injection.repository) {
    var banner:MutableLiveData<List<Banner>> = MutableLiveData()
    var brend:MutableLiveData<List<Brand>> = MutableLiveData()
    var hotGoods: MutableLiveData<List<HotGoods>> = MutableLiveData()
    var newGoods:MutableLiveData<List<NewGoods>> = MutableLiveData()
    var channel:MutableLiveData<List<Channel>> = MutableLiveData()
    var category:MutableLiveData<List<Category>> = MutableLiveData()
    var topic:MutableLiveData<List<Topic>> = MutableLiveData()
    var loadStatue:MutableLiveData<Int> = MutableLiveData()

    //获取首页的数据
    fun getHome(){
        viewModelScope.launch {
            var result = repository.getHome()
            if(result.errno == 0){
                banner.postValue(result.data.banner)
                brend.postValue(result.data.brandList)
                hotGoods.postValue(result.data.hotGoodsList)
                newGoods.postValue(result.data.newGoodsList)
                channel.postValue(result.data.channel)
                category.postValue(result.data.categoryList)
                topic.postValue(result.data.topicList)
            }else if(result.errno == 665){
                refreshToken
            }
        }
    }
}