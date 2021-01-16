package com.shop.ui.home.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shop.R
import com.shop.adapter.home.MyBannerAdapter
import com.shop.databinding.ActivityBannerHomeBinding

class BannerHomeActivity : AppCompatActivity() {

    var mBinding:ActivityBannerHomeBinding? = null
    var viewModel:BindBannerViewModel? = null
    var mAdapter: MyBannerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_banner_home)
        viewModel = ViewModelProvider(this).get(BindBannerViewModel::class.java)
        initView()
        initVM()
        viewModel!!.homeData()
    }



    private fun initView() {
        mAdapter = MyBannerAdapter()
    }

    private fun initVM() {
        viewModel!!.banner.observe(this, Observer {
            mBinding!!.ban.setImages(it).setImageLoader(mAdapter).start()
        })
    }
}