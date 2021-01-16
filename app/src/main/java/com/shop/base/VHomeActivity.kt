package com.shop.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shop.R

class VHomeActivity:AppCompatActivity() {

    lateinit var homeVM: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initVm()
        homeVM.loadHomeData()
    }

    private fun initVm(){
        //初始化ViewModel
        homeVM = ViewModelProvider(this).get(HomeViewModel::class.java)

        //监听网络状态的变化
        homeVM.loadStatue.observe(this, Observer { status ->
            if(status == -1){
                Log.i("TAG","数据加载失败")
            }
        })

        /**
         * 监听轮播图数据的变化
         */
        homeVM.banner.observe(this, Observer { banner ->
            Log.i("TAG","banner");
        })

        /**
         * 品牌直供
         */
        homeVM.brend.observe(this, Observer {
            Log.i("TAG","brend");
        })

        /**
         * 热门商品
         */
        homeVM.hotGoods.observe(this, Observer {
            Log.i("TAG","hotGoods");
        })

        /**
         *
         */
        homeVM.newGoods.observe(this, Observer {
            Log.i("TAG","newGoods");
        })

    }
}