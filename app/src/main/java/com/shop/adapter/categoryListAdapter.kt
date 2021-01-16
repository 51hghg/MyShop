package com.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.bean.Goods
import kotlinx.android.synthetic.main.item_cate.view.*

class categoryListAdapter(private val context: Context?, private val list: List<Goods>)
    : RecyclerView.Adapter<categoryListAdapter.ViewHolder>() {
    class ViewHolder(item : View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): categoryListAdapter.ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.item_cate,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: categoryListAdapter.ViewHolder, position: Int) {
        with(holder?.itemView){
            Glide.with(context).load(list.get(position).list_pic_url).into(img_cate)
            name_cate?.text = list.get(position).name
            pri_cate?.text = list.get(position).retail_price
        }
    }
}