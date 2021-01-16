package com.shop.adapter.home.brand

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.base.BaseAdapter
import com.shop.bean.DataX

class BrandInAdapter(context: Context, list: List<DataX>,layouts: SparseArray<Int>): BaseAdapter<DataX>(context, list,layouts) {
    override fun layoutId(position: Int): Int {
        return R.layout.item_tv_brand

//        var url =  list.get(position).list_pic_url
//        if(TextUtils.isEmpty(url)){
//            return R.layout.layout_hotgood_noimage
//        }
//        return R.layout.layout_hotgood
    }

    override fun bindData(binding: ViewDataBinding, data: DataX) {
        Glide.with(context).load(data.app_list_pic_url).into(binding.root.findViewById(R.id.img_tvbrand))
    }
    fun refreshData(lt : List<DataX>){
        list = lt
        notifyDataSetChanged()
    }
}