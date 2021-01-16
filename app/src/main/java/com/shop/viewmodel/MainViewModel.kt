package com.shop.viewmodel

import androidx.fragment.app.Fragment
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import com.shop.ui.car.ShopFragment
import com.shop.ui.home.HomeFragment
import com.shop.ui.me.MineFragment
import com.shop.ui.sort.SortFragment
import com.shop.ui.topic.TopicFragment

class MainViewModel:BaseViewModel(Injection.repository) {

    var fragments:MutableList<Fragment> = mutableListOf()

    init {
        fragments.add(HomeFragment.instance)
        fragments.add(TopicFragment.instance)
        fragments.add(SortFragment.instance)
        fragments.add(ShopFragment.instance)
        fragments.add(MineFragment.instance)
    }




}