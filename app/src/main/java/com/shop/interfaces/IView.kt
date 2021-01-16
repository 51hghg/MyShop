package com.shop

interface IView {

    fun showLoading()

    /**
     * 显示提示信息
     */
    fun showTips(tips:String)

}