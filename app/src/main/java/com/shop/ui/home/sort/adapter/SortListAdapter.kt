package com.shop.ui.home.sort.adapter

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.shop.BR
import com.shop.R
import com.shop.base.BaseAdapter
import com.shop.base.BaseAdapter1
import com.shop.base.IItemClick
import com.shop.bean.SubCategory

class SortListAdapter(
    context: Context,
    list:List<SubCategory>,
    layouts:SparseArray<Int>,
    var click: IItemClick<SubCategory>
): BaseAdapter1<SubCategory>(context,list,layouts,click) {
    override fun layoutId(position: Int): Int {
        return R.layout.layout_sortlist_item
    }

    override fun bindData(binding: ViewDataBinding, data: SubCategory, layId: Int) {
        binding.setVariable(BR.sortclick,click)
    }
}