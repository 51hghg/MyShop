package com.shop.ui.home.shouye

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.base.BaseViewModel
import com.shop.bean.Banner
import com.shop.bean.HomeData
import com.shop.bean.HotGoods
import com.shop.net.Injection
import kotlinx.coroutines.launch

class ShouViewModel :BaseViewModel(Injection.repository) {

    var hotGoods: MutableLiveData<List<HotGoods>> = MutableLiveData()
    //获取首页的数据
    fun getHome(){
        viewModelScope.launch {
            var result = repository.getHome()
            if(result.errno == 0){
                hotGoods.postValue(result.data.hotGoodsList)
            }else if(result.errno == 665){
                refreshToken
            }
        }
    }
}