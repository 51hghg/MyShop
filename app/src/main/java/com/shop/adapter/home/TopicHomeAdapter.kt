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
import com.shop.bean.HotGoods
import com.shop.bean.Topic
import kotlinx.android.synthetic.main.layout_hot_item.view.*
import kotlinx.android.synthetic.main.layout_topic_item.view.*

class TopicHomeAdapter(var context: Context?, var list: List<Topic> = listOf<Topic>()):
    RecyclerView.Adapter<BaseViewHolder>() {
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
        return R.layout.layout_topic_item
    }
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var binding : ViewDataBinding = holder.dataBinding
        binding.setVariable(BR.vmTopic,list.get(position))

        with(holder?.itemView){
            Glide.with(context).load(list.get(position).item_pic_url).into(img_topic)
        }
    }

    fun refreshData(lt : List<Topic>){
        list = lt
        notifyDataSetChanged()
    }
}