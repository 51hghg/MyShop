package com.shop.tongpao

import android.content.Context
import android.text.TextUtils
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.base.BaseAdapter
import com.shop.bean.DataX

class HotAdapter(context: Context, list: List<Stu>, layouts: SparseArray<Int>): BaseAdapter<Stu>(context,list,layouts){
    override fun layoutId(position: Int): Int {

        var url =  list.get(position).filePathList
        for (i in url.indices){
            val length = url.size
            if(length == 3){
                return R.layout.item_tong_img2
            }else if(length == 1){
                return R.layout.item_tong_img1
            }
        }
        return R.layout.item_tong_img
    }

    override fun bindData(binding: ViewDataBinding, data: Stu) {

        var url =  data.filePathList
        for (i in url.indices){
            val length = url.size
            if(length == 3){
                Glide.with(context).load(url.get(0).filePath).into(binding.root.findViewById(R.id.img11))
                Glide.with(context).load(url.get(1).filePath).into(binding.root.findViewById(R.id.img22))
                Glide.with(context).load(url.get(2).filePath).into(binding.root.findViewById(R.id.img33))
            }else if(length == 1){
                Glide.with(context).load(url.get(0).filePath).into(binding.root.findViewById(R.id.img12))
            }
        }
    }


    fun refreshData(lt : List<Stu>){
        list = lt
        notifyDataSetChanged()
    }
}