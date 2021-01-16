package com.shop.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.BR
import com.shop.R
import com.shop.base.BaseViewHolder
import com.shop.bean.Brand
import kotlinx.android.synthetic.main.layout_brand_item.view.*


class BindHomeAdapter(var context: Context?, var list:List<Brand> = listOf<Brand>()): RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var binding:ViewDataBinding = holder.dataBinding
        //把数据绑定到item的xml界面
        binding.setVariable(BR.vmBrand,list.get(position))

        with(holder?.itemView){
            Glide.with(context).load(list.get(position).new_pic_url).into(img)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_brand_item
    }

    /**
     * 加载完数据刷新到界面的data
     */
    fun refreshData(lt:List<Brand>){
        list = lt
        notifyDataSetChanged()
    }

}