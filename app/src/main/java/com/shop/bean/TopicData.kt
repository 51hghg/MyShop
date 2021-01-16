package com.shop.bean


data class TopicData(
    val count: Int,
    val currentPage: Int,
    val `data`: List<DataX>,
    val pageSize: Int,
    val totalPages: Int
)
{
    data class DataX(
        val id: Int,
        val price_info: Int,
        val scene_pic_url: String,
        val subtitle: String,
        val title: String
    )
}

