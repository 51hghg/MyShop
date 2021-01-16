package com.shop.bean

data class HotgoodsData(
    val count: Int,
    val currentPage: Int,
    val `data`: List<DataX>,
    val filterCategory: List<FilterCategory>,
    val goodsList: List<Goods1>,
    val pageSize: Int,
    val totalPages: Int
)
{

    data class DataX1(
        val id: Int,
        val list_pic_url: String,
        val name: String,
        val retail_price: String
    )

    data class FilterCategory(
        val checked: Boolean,
        val id: Int,
        val name: String
    )

    data class Goods1(
        val id: Int,
        val list_pic_url: String,
        val name: String,
        val retail_price: String
    )
}