package com.shop.adapter.home.brand

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
import com.shop.bean.DataX
import kotlinx.android.synthetic.main.item_brand.view.*
import kotlinx.android.synthetic.main.item_tv_brand.view.*

class BrandInfoAdapter(var context: Context,var list: List<DataX> = listOf<DataX>()): RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_tv_brand
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var binding : ViewDataBinding = holder.dataBinding

        with(holder?.itemView!!){
            Glide.with(context).load(list.get(position).app_list_pic_url).into(img_tvbrand)
        }
        binding.setVariable(BR.vmBrandInfo,list.get(position))
    }

    fun refreshData(lt : List<DataX>){
        list = lt
        notifyDataSetChanged()
    }
}