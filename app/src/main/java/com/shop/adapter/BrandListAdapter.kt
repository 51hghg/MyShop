package com.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.bean.Brand
import kotlinx.android.synthetic.main.item_brand.view.*

class BrandListAdapter(private val context: Context?,
                       private val list: List<Brand>):
    RecyclerView.Adapter<BrandListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandListAdapter.ViewHolder {
        val view :View = LayoutInflater.from(context).inflate(R.layout.item_brand,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BrandListAdapter.ViewHolder, position: Int) {
        with(holder?.itemView!!){
            Glide.with(context).load(list.get(position).new_pic_url).into(img)
            name?.text = list.get(position).name
            price?.text = list.get(position).floor_price+"元起"
        }
    }

    class ViewHolder(item:View):RecyclerView.ViewHolder(item)
}