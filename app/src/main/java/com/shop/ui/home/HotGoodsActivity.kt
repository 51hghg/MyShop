package com.shop.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.shop.BR
import com.shop.R
import com.shop.app.Constants
import com.shop.base.BaseActivity
import com.shop.base.IItemClick
import com.shop.bean.HotgoodsData
import com.shop.databinding.ActivityHotGoodsBinding
import com.shop.viewmodel.BindHotGoodsViewModel
import kotlinx.android.synthetic.main.activity_hot_goods.*
import kotlinx.android.synthetic.main.pw.*

class HotGoodsActivity(
        var lid: Int = R.layout.activity_hot_goods
) : BaseActivity<BindHotGoodsViewModel,ActivityHotGoodsBinding>(lid,BindHotGoodsViewModel::class.java) {

        var list: MutableList<HotgoodsData.Goods1> = mutableListOf()
        lateinit var goodlistAdapter:HotgoodsAdapter

        //是否是新品
        var isNew = 1
        var page = 1
        var size = 1000
        var order: String? = null
        var sort: String? = null
        var categoryId = 0

        override fun initView() {
                mDataBinding.recyGoodList.layoutManager = GridLayoutManager(this,2)
                var array = SparseArray<Int>()
                array.put(R.layout.item_goods,BR.good)
                goodlistAdapter = HotgoodsAdapter(this,list,array,itemClick())
                mDataBinding.recyGoodList.adapter =goodlistAdapter

                order = Constants.ASC
                sort = Constants.DEFAULT
                categoryId = 0

                mDataBinding.layoutPrice.setTag(0)
                mDataBinding.txtAll.setTextColor(Color.parseColor("#ff0000"))
                mDataBinding.setVariable(R.layout.activity_hot_goods,BR.vmHotgoods)
                mDataBinding!!.setVariable(BR.clickEvt,ClickEvt())
        }

        override fun initVM() {
                mViewModel.goods1.observe(this, Observer {
                        updateGoodList(it.goodsList)
                        Log.i("TAG", "initVM: "+it)

                        if (sort === Constants.CATEGORY) {
                                getFilter(it.filterCategory)
                                sort = Constants.DEFAULT
                        }
                })
        }

        private fun getFilter(filterCategory: List<HotgoodsData.FilterCategory>) {
                val inflate: View =
                        LayoutInflater.from(this).inflate(R.layout.pw, null)
                val pw = PopupWindow(inflate, ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT)
                val ll_pw = inflate.findViewById<LinearLayout>(R.id.ll_pw)

                if (filterCategory.size > 0) {
                        for (i in filterCategory.indices) {
                                val textView: TextView = buildLabel(filterCategory[i].name)
                                ll_pw.addView(textView)
                                textView.setOnClickListener {
                                        val map =
                                                HashMap<String, String>()
                                        map["isNew"] = 1.toString()
                                        map["categoryId"] = java.lang.String.valueOf(filterCategory[i].id)
                                        mViewModel.getGoodList(map)
                                        pw.dismiss()
                                }
                        }
                }

                //显示在控件的下面
                pw.showAsDropDown(layout_sort, 0, 0)
                pw.isOutsideTouchable = true
        }
//
        private fun buildLabel(text: String): TextView {
                val textView = TextView(this)
                textView.text = text
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

                textView.paddingLeft.compareTo(10)
                textView.paddingRight.compareTo(10)
                textView.paddingTop.compareTo(6)
                textView.paddingBottom.compareTo(6)

                textView.marginBottom.compareTo(10)
                textView.marginTop.compareTo(10)
                textView.marginLeft.compareTo(200)
                textView.marginRight.compareTo(200)

                textView.gravity = Gravity.CENTER
                return textView
        }

        override fun initData() {
                sort=Constants.DEFAULT
                order=Constants.ASC

                getParam()?.let { mViewModel.getGoodList(it) }
        }

        override fun initVariable() {

        }

        fun updateGoodList(list: List<HotgoodsData.Goods1>){
                this.list.clear()
                this.list.addAll(list)
                goodlistAdapter.notifyDataSetChanged()
        }

        private fun getParam(): HashMap<String, String>? {
                val map = HashMap<String, String>()
                map["isNew"] = isNew.toString()
                map["page"] = page.toString()
                map["size"] = size.toString()
                map["order"] = order!!
                map["sort"] = sort!!
                map["category"] = categoryId.toString()
                return map
        }

        /**
         * 按价格升序排序
         */
        @SuppressLint("ResourceType")
        private fun priceStateUp() {
                mDataBinding.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select)
                mDataBinding.imgArrowDown.setImageResource(R.mipmap.downs)
                mDataBinding.txtPrice.setTextColor(Color.parseColor(getString(R.color.red)))
        }

        /**
         * 价格的降序排列
         */
        @SuppressLint("ResourceType")
        private fun priceStateDown() {
                mDataBinding.imgArrowUp.setImageResource(R.mipmap.ups)
                mDataBinding.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select)
                mDataBinding.txtPrice.setTextColor(Color.parseColor(getString(R.color.red)))
        }

        /**
         * 重置条件选择的所有状态
         */
        @SuppressLint("ResourceType")
        private fun resetPriceState() {
                mDataBinding.imgArrowUp.setImageResource(R.mipmap.ups)
                mDataBinding.imgArrowDown.setImageResource(R.mipmap.downs)
                mDataBinding.txtPrice.setTextColor(Color.parseColor(getString(R.color.black)))
                mDataBinding.txtAll.setTextColor(Color.parseColor(getString(R.color.black)))
                mDataBinding.txtSort.setTextColor(Color.parseColor(getString(R.color.black)))
                mDataBinding.layoutPrice.setTag(0)
        }

        inner class itemClick: IItemClick<HotgoodsData.Goods1> {
                override fun itemClick(data:HotgoodsData.Goods1) {

                }
        }

        inner class ClickEvt{
                fun clickPrice(){
                        val tag = mDataBinding.layoutPrice.getTag() as Int
                        if (tag == 0) {
                                resetPriceState()
                                priceStateUp()
                                mDataBinding.layoutPrice.setTag(1)
                                order = Constants.ASC
                        } else if (tag == 1) {
                                resetPriceState()
                                priceStateDown()
                                mDataBinding.layoutPrice.setTag(0)
                                order = Constants.DESC
                        }
                        sort = Constants.PRICE
                        getParam()?.let { mViewModel.getGoodList(it) }
                }

                fun clickAll(){
                        resetPriceState()
                        mDataBinding.txtAll.setTextColor(Color.parseColor("#ff0000"))
                        sort = Constants.DEFAULT
                        categoryId = 0
                        getParam()?.let { mViewModel.getGoodList(it) }
                }

                fun clickSort(){
                        resetPriceState()
                        mDataBinding.txtSort.setTextColor(Color.parseColor("#ff0000"))
                        sort = Constants.CATEGORY
                        getParam()?.let { mViewModel.getGoodList(it) }
                }
        }
}