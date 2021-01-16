package com.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.bean.HotGoods
import kotlinx.android.synthetic.main.item_hot.view.*

class HotListAdapter(private val context: Context?, private val list: List<HotGoods>)
    : RecyclerView.Adapter<HotListAdapter.ViewHolder>() {
    class ViewHolder(item: View): RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotListAdapter.ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_hot,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: HotListAdapter.ViewHolder, position: Int) {
        with(holder?.itemView){
            Glide.with(context).load(list.get(position).list_pic_url).into(img_hot)
            hot_name?.text = list.get(position).name
            pri_hot?.text = list.get(position).retail_price
            brief?.text = list.get(position).goods_brief
        }
    }
}