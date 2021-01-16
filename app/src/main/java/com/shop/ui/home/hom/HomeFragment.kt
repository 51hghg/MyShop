package com.shop.ui.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.adapter.categoryListAdapter
import com.shop.adapter.home.*
import com.shop.base.BaseFragment
import com.shop.databinding.FragmentHomeBinding
import com.shop.ui.home.hom.BrandInfoActivity
import com.shop.viewmodel.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_home2.*

class HomeFragment(val lid:Int):BaseFragment<HomeViewModel,FragmentHomeBinding>(lid,HomeViewModel::class.java) {

    var mBanner: MyBannerAdapter? = null
    var mBrand: BindHomeAdapter? = null
    var mAdapter : HotHomeAdapter? = null
    var mNew : NewHomeAdapter? = null
    var mCate : CategoryListAdapter? = null
    var mTopic : TopicHomeAdapter? = null

    /**
     * 提供Homefragment实例
     */
    companion object{
        val instance:HomeFragment by lazy { HomeFragment(R.layout.fragment_home) }
    }

    override fun initView() {
        //banner
        mBanner = MyBannerAdapter()

        //brand
        val layoutManager = GridLayoutManager(activity,2)
        mDataBinding!!.rlvBrand.layoutManager = layoutManager
        mBrand = BindHomeAdapter(context)
        mDataBinding!!.rlvBrand.adapter = mBrand

        //hot
        val layoutManager2 = LinearLayoutManager(context)
        mDataBinding!!.rlvHot.layoutManager = layoutManager2
        mAdapter = HotHomeAdapter(context)
        mDataBinding!!.rlvHot.adapter = mAdapter

        //new
        val layoutManager3 = GridLayoutManager(activity,2)
        mDataBinding!!.rlvNew.layoutManager = layoutManager3
        mNew = NewHomeAdapter(context)
        mDataBinding!!.rlvNew.adapter = mNew

        //topic
        val layoutManager4 = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
        mDataBinding!!.rlvTopic.layoutManager = layoutManager4
        mTopic = TopicHomeAdapter(context)
        mDataBinding!!.rlvTopic.adapter = mTopic

        //品牌的跳转
        tv_brand.setOnClickListener(View.OnClickListener {
            var intent = Intent(context,BrandInfoActivity::class.java)
            startActivity(intent)
        })

        hot_goods.setOnClickListener {
            var intent = Intent(context,HotGoodsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initVM() {
        initBan()
        initBrand()
        initHotGoods()
        initNewHome()
        initChannel()
        initCategory()
        initTopic()
    }

    override fun initData() {
        mViewModel.getHome()
    }

    override fun initVariable() {
    }

    private fun initBan() {
        mViewModel!!.banner.observe(this, Observer {
            mDataBinding!!.bannerHome.setImages(it).setImageLoader(mBanner).start()
        })
    }

    private fun initBrand() {
        mViewModel!!.brend.observe(this, Observer {
            mBrand!!.refreshData(it)
        })
    }

    private fun initHotGoods() {
        mViewModel.hotGoods.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }

    private fun initNewHome() {
        mViewModel!!.newGoods.observe(this, Observer {
            mNew!!.refreshData(it)
        })
    }

    private fun initTopic() {
        mViewModel!!.topic.observe(this, Observer {
            mTopic!!.refreshData(it)
        })
    }

    private fun initChannel() {
        mViewModel!!.channel.observe(this, Observer {
            liness.removeAllViews();//清空布局
            for (i in it .indices){
            val view : View = LayoutInflater.from(activity).inflate(R.layout.home_item_channel,null)
            liness.addView(view)

            val img_channel = view.findViewById<ImageView>(R.id.img_channel)
            val tv_channel = view.findViewById<TextView>(R.id.tv_channel)
            Glide.with(this).load(it[i].icon_url).into(img_channel)
            tv_channel.text = it[i].name
            //权重设置
            view.setLayoutParams(
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
                )
            )
        }
        })

    }

    private fun initCategory() {
        mViewModel!!.category.observe(this, Observer {

            for (i in it.indices){
//                mCate!!.refreshData(it[i].goodsList)
                val view : View = LayoutInflater.from(activity).inflate(R.layout.home_item_sss,null)
                liner.addView(view)
                Log.e("111", "showCategory: "+i )
                val text = view.findViewById<TextView>(R.id.txt_home_title)
                text.text = (it[i].name)
                val goodsList =  it[i].goodsList
                val recy_home: RecyclerView = view.findViewById(R.id.recy_home)
                recy_home.layoutManager = GridLayoutManager(activity,2)
                val categoryListAdapter = categoryListAdapter(context,goodsList)
                recy_home.adapter = categoryListAdapter
            }
        })
    }
}