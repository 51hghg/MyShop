package com.shop.ui.home.shouye

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityShouBinding
import com.shop.adapter.home.HotHomeAdapter

class ShouActivity : BaseActivity<ShouViewModel,ActivityShouBinding>(R.layout.activity_shou,ShouViewModel::class.java) {
    var mAdapter : HotHomeAdapter? = null
    override fun initView() {
        val layoutManager2 = LinearLayoutManager(this)
        mDataBinding!!.rlvHot.layoutManager = layoutManager2
        mAdapter = HotHomeAdapter(this)
        mDataBinding!!.rlvHot.adapter = mAdapter
    }

    override fun initVM() {
        mViewModel.hotGoods.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }

    override fun initData() {
        mViewModel.getHome()
    }

    override fun initVariable() {
    }
}