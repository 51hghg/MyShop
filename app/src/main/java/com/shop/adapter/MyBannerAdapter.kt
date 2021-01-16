package com.shop.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shop.bean.Banner
import com.youth.banner.loader.ImageLoader

class MyBannerAdapter: ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        var img = path as Banner
        Glide.with(context!!).load(img.image_url).into(imageView!!)
    }

}