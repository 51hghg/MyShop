package com.shop.viewmodel.sort

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.base.BaseViewModel
import com.shop.bean.GoodListData
import com.shop.bean.SortData
import com.shop.net.Injection
import kotlinx.coroutines.launch

class GoodListViewModel:BaseViewModel(Injection.repository) {
    var goodlist: MutableLiveData<GoodListData> = MutableLiveData()

    var sortData: MutableLiveData<SortData> = MutableLiveData()  //竖导航数据

    fun getGoodLiatData(id:String){
        viewModelScope.launch {
            var result = repository.getGoodList1(id)
            if (result.errno==0){
                goodlist.postValue(result.data)
            }
        }
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
}