package com.shop.viewmodel.topic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shop.base.BaseViewModel
import com.shop.bean.TopicData
import com.shop.net.Injection
import kotlinx.coroutines.launch

class TopicViewModel:BaseViewModel(Injection.repository) {
    var dataX : MutableLiveData<List<TopicData.DataX>> =
        MutableLiveData(listOf())

    fun getTopic(){
        viewModelScope.launch {
            var result = repository.getTopic()
            if(result.errno == 0){
                dataX.postValue(result.data.data)
            }
        }
    }
}

