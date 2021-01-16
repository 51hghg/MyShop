package com.shop.app

import android.app.Application
import com.shop.utils.MyMmkv

class MyApp:Application() {

    companion object{
        var instance:MyApp? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MyMmkv.initMMKV()
    }

}