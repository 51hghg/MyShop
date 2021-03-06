package com.shop.adapter.home

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shop.bean.Banner
import com.shop.bean.Brand
import com.youth.banner.loader.ImageLoader

class MyBannerAdapter: ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        var item = path as Banner
        Glide.with(context!!).load(item.image_url).into(imageView!!)
    }


}