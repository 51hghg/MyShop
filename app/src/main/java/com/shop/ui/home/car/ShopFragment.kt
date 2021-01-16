package com.shop.ui.car

import androidx.fragment.app.Fragment
import com.shop.R
import com.shop.base.BaseFragment
import com.shop.base.BaseViewModel
import com.shop.databinding.FragmentShopBinding
import com.shop.ui.sort.SortFragment
import com.shop.viewmodel.shop.ShopViewModel

class ShopFragment(val lid:Int):BaseFragment<ShopViewModel,FragmentShopBinding>(lid,ShopViewModel::class.java) {
    companion object{
        val instance by lazy { ShopFragment(R.layout.fragment_shop) }
    }

    override fun initView() {
    }

    override fun initVM() {
    }

    override fun initData() {
    }

    override fun initVariable() {
    }
}