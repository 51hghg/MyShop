package com.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.bean.Topic
import kotlinx.android.synthetic.main.item_topic.view.*

class TopicAdapter(private val context: Context?, private val list:List<Topic>)
    : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {
    class ViewHolder(item: View):RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicAdapter.ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.item_topic,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TopicAdapter.ViewHolder, position: Int) {
        with(holder?.itemView){
            Glide.with(context).load(list.get(position).item_pic_url).into(img_topic)
            title?.text = list.get(position).title
            price_info?.text = list.get(position).price_info+"元起"
            subtitle?.text = list.get(position).subtitle
        }
    }
}