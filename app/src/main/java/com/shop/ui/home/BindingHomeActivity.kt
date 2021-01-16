package com.shop.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shop.R
import com.shop.adapter.home.BindHomeAdapter
import com.shop.adapter.home.HotHomeAdapter
import com.shop.adapter.home.NewHomeAdapter
import com.shop.databinding.ActivityBindingHomeBinding
import com.shop.viewmodel.BindHomeViewModel
class BindingHomeActivity : AppCompatActivity() {

    var mBinding:ActivityBindingHomeBinding? = null
    var viewModel:BindHomeViewModel? = null

    var mAdapter: BindHomeAdapter? = null
    var mAdapter1: NewHomeAdapter? = null
    var mAdapter2: HotHomeAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_binding_home)
        viewModel = ViewModelProvider(this).get(BindHomeViewModel::class.java)
        initView()
        initVM()
        viewModel!!.homeData()
    }

    private fun initView() {

        //brand
        val layoutManager = GridLayoutManager(this,2)
        mBinding!!.recyBrend.layoutManager = layoutManager
        mAdapter = BindHomeAdapter(this)
        mBinding!!.recyBrend.adapter = mAdapter

        //new
        val layoutManager1 = GridLayoutManager(this,2)
        mBinding!!.recyNew.layoutManager = layoutManager1
        mAdapter1 = NewHomeAdapter(this)
        mBinding!!.recyNew.adapter = mAdapter1

        //hot
        val layoutManager2 = LinearLayoutManager(this)
        mBinding!!.recyHot.layoutManager = layoutManager2
        mAdapter2 = HotHomeAdapter(this)
        mBinding!!.recyHot.adapter = mAdapter2


    }



    private fun initVM(){
        viewModel!!.brand.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
        viewModel!!.newGoods.observe(this, Observer {
            mAdapter1!!.refreshData(it)
        })
        viewModel!!.hotGoods.observe(this, Observer {
            mAdapter2!!.refreshData(it)
        })
    }


}