package com.shop.ui.home

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.R
import com.shop.base.BaseAdapter
import com.shop.base.BaseAdapter1
import com.shop.base.BaseViewHolder
import com.shop.base.IItemClick
import com.shop.bean.HotgoodsData

class HotgoodsAdapter(
    context: Context,
    list:List<HotgoodsData.Goods1>,
    layouts: SparseArray<Int>,
    click: IItemClick<HotgoodsData.Goods1>
): BaseAdapter1<HotgoodsData.Goods1>(context,list,layouts,click){
    override fun layoutId(position: Int): Int {
        return R.layout.item_goods
    }

    override fun bindData(binding: ViewDataBinding, data: HotgoodsData.Goods1, layId: Int) {

        Glide.with(context).load(data.list_pic_url).into(binding.root.findViewById(R.id.img))
    }
}