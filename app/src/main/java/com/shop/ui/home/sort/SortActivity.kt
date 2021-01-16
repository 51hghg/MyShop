package com.shop.ui.home.sort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.shop.BR
import com.shop.R
import com.shop.base.BaseActivity
import com.shop.bean.SortData
import com.shop.databinding.ActivityShouBinding
import com.shop.databinding.ActivityShouBindingImpl
import com.shop.databinding.ActivitySortBinding
import com.shop.viewmodel.sort.GoodListViewModel
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView
import q.rorbin.verticaltablayout.widget.QTabView
import q.rorbin.verticaltablayout.widget.TabView

class SortActivity(
        var lid: Int = R.layout.activity_sort
) : BaseActivity<GoodListViewModel, ActivitySortBinding>(lid,GoodListViewModel::class.java) {

        private lateinit var tabAdapter: TabAdapter

        private var sortList:MutableList<SortData.SubCategory> = mutableListOf()

        override fun initView() {
//                //竖导航tab的点击监听
                mDataBinding.tablayout.addOnTabSelectedListener(object :
                        TabLayout.OnTabSelectedListener {
                        override fun onTabReselected(p0: TabLayout.Tab?) {
                        }

                        override fun onTabUnselected(p0: TabLayout.Tab?) {
                        }

                        override fun onTabSelected(p0: TabLayout.Tab?) {
                        }
                })
                tabAdapter = MyTabAdapter()
//                mDataBinding.tablayout.setTabAdapter(tabAdapter)
        }

        override fun initVM() {
                //竖导航 加载数据回来的监听
                mViewModel.sortData.observe(this, Observer {
                        //动态的添加竖导航tab
                        for (item in it.currentCategory.subCategoryList) {
                                //创建显示tab的样式组件
                                var tabTitle = ITabView.TabTitle.Builder().setContent(item.name).build()
                                //创建tab的显示View，并且添加到竖导航
//                                mDataBinding.tablayout.addTab(QTabView(this).setTitle(tabTitle))
                        }

                        mDataBinding.setVariable(BR.vmSortListData,mViewModel)

                        //默认请求第一个tab对应的列表数据
                        if(it.currentCategory.subCategoryList.size > 0){
                                mViewModel.getGoodLiatData(it.currentCategory.subCategoryList.get(0).id.toString())
                        }
                })
        }

        override fun initData() {
        }

        override fun initVariable() {
        }

        //adapter 和 viewpager结合使用
        inner class MyTabAdapter: TabAdapter {
                override fun getCount(): Int {
                        return sortList.size
                }

                override fun getBadge(position: Int): ITabView.TabBadge {
                        TODO("Not yet implemented")
                }

                override fun getIcon(position: Int): ITabView.TabIcon {
                        TODO("Not yet implemented")
                }

                override fun getTitle(position: Int): ITabView.TabTitle {
                        TODO("Not yet implemented")
                }

                override fun getBackground(position: Int): Int {
                        TODO("Not yet implemented")
                }

        }

}