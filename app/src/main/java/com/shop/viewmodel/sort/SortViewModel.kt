package com.shop.viewmodel.sort

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.base.BaseViewModel
import com.shop.bean.SortData
import com.shop.bean.SortListData
import com.shop.net.Injection
import kotlinx.coroutines.launch

class SortViewModel:BaseViewModel(Injection.repository) {
    var sortData: MutableLiveData<SortData> = MutableLiveData()  //竖导航数据

    var sortDataList: MutableLiveData<SortListData> = MutableLiveData()


    lateinit var bannerUrl:String //当前竖导航选中的tab所对应的banner数据
    lateinit var desc:String
    lateinit var name:String


    /**
     * 刷新当前的tab所对应的数据
     */
    fun updateCurrentTab(data:SortData.Category){
        bannerUrl = data.wap_banner_url
        desc = data.front_desc
        name=data.name
    }

    /**
     * 获取竖导航列表
     */
    fun getSortData(){
        viewModelScope.launch {
            var result = repository.getSortData()
            if(result.errno == 0){
                sortData.postValue(result.data)
            }
        }
    }

    /**
     * 竖导航列表数据
     */
    fun getSortListData(id:String){
        viewModelScope.launch {
            var result = repository.getCatalogData(id)
            if(result.errno == 0){
                sortDataList.postValue(result.data)
            }
        }
    }
}