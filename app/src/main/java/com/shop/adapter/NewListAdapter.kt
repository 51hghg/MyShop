package com.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.bean.NewGoods
import kotlinx.android.synthetic.main.item_new.view.*

class NewListAdapter(private val context: Context?, private val list: List<NewGoods>)
    : RecyclerView.Adapter<NewListAdapter.ViewHolder>() {
    class ViewHolder(item:View) :RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewListAdapter.ViewHolder {
        val view :View = LayoutInflater.from(context).inflate(R.layout.item_new,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewListAdapter.ViewHolder, position: Int) {
        with(holder?.itemView!!){
            Glide.with(context).load(list.get(position).list_pic_url).into(img_new)
            new_name?.text = list.get(position).name
            pri?.text = list.get(position).retail_price+"￥"
        }
    }
}