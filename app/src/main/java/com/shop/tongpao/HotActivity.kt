package com.shop.tongpao

import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shop.BR
import com.shop.R
import com.shop.adapter.home.brand.BrandInAdapter
import com.shop.base.BaseActivity
import com.shop.bean.DataX
import com.shop.databinding.ActivityHotBinding

class HotActivity:BaseActivity<HotViewModel,ActivityHotBinding>(R.layout.activity_hot,HotViewModel::class.java) {
    var mAdapter : HotAdapter? = null
    var list: List<Stu> = arrayListOf()
    override fun initView() {
        val layoutManager = LinearLayoutManager(this)
        mDataBinding!!.tongpaoRlv.layoutManager = layoutManager
    }

    override fun initVM() {
        mViewModel.stu.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }

    override fun initData() {
        mViewModel.getMore()

        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var array = SparseArray<Int>()
        array.put(R.layout.item_tong_img, BR.img0)
        array.put(R.layout.item_tong_img1,BR.img1)
        array.put(R.layout.item_tong_img2,BR.img2)
        mAdapter = HotAdapter(this,list,array)
        mDataBinding!!.tongpaoRlv.adapter = mAdapter
    }

    override fun initVariable() {

    }

}