package com.shop.ui.home.hom

import android.os.Handler
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shop.BR
import com.shop.R
import com.shop.adapter.home.brand.BrandInAdapter
import com.shop.base.BaseActivity
import com.shop.databinding.ActivityBrandInfoBinding
import com.shop.bean.DataX
import com.shop.viewmodel.home.BindBrandViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BrandInfoActivity : BaseActivity<BindBrandViewModel,ActivityBrandInfoBinding>(R.layout.activity_brand_info,
    BindBrandViewModel::class.java) {

    var mAdapter : BrandInAdapter? = null
    var list: List<DataX> = arrayListOf()

    override fun initView() {
        val layoutManager = LinearLayoutManager(this)
        mDataBinding!!.rlvBrandInfo.layoutManager = layoutManager
    }
    override fun initData() {
        mViewModel.getBrand()


        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var array = SparseArray<Int>()
        array.put(R.layout.item_tv_brand,BR.vmBrandInfo)
//        array.put(R.layout.layout_hotgood_noimage,BR.vmHotGoodNoImage)
        mAdapter = BrandInAdapter(this,list,array)
        mDataBinding!!.rlvBrandInfo.adapter = mAdapter
    }

    override fun initVariable() {

    }

    override fun initVM() {
        mViewModel!!.dataX.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }


}