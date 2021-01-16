package com.shop.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.shop.R
import com.shop.adapter.*
import com.shop.bean.*
import com.shop.adapter.home.MyBannerAdapter
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        loadHomeData()
    }

    private fun loadHomeData(){
        MainScope().launch {
            //拿到数据
            var result = homeData()
            showBanner(result.banner)
            showChannel(result.channel)
            showBrand(result.brandList)
            showNewList(result.newGoodsList)
            showHotList(result.hotGoodsList)
            showTopic(result.topicList)
            showCategory(result.categoryList)
        }
        Log.d("TAG","loadHomeData")
    }



    suspend fun homeData():HomeData{
        val url = "https://cdplay.cn/api/index"
        return get(url)
    }

    suspend fun get(netUrl:String) = withContext(Dispatchers.IO){
        var url = URL(netUrl)
        (url.openConnection() as? HttpsURLConnection).run {
            var sb = StringBuffer()
            var streamReader = InputStreamReader(this!!.inputStream,"utf-8")
            var reader = BufferedReader(streamReader)
            reader.use {
                var temp = reader.readLine()
                if(temp != null) sb.append(temp)
            }
            streamReader.close()
            reader.close()
            inputStream.close()
            val homeData = Gson().fromJson<HomeData>(sb.toString(),HomeData::class.java)
            return@run homeData
        }
    }

    //显示banner
    private fun showBanner(banner: List<Banner>) {
        banner_home.setImages(banner).setImageLoader(MyBannerAdapter()).start()
    }

    //自定义选择
    private fun showChannel(channel: List<Channel>) {
        for (i in channel.indices){
            val view : View = LayoutInflater.from(this).inflate(R.layout.home_item_channel,null)
            liness.addView(view)

            val img_channel = view.findViewById<ImageView>(R.id.img_channel)
            val tv_channel = view.findViewById<TextView>(R.id.tv_channel)
            Glide.with(this).load(channel[i].icon_url).into(img_channel)
            tv_channel.text = channel[i].name
            //权重设置
            view.setLayoutParams(
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
                )
            )
        }
    }
    //品牌制造
    private fun showBrand(brandList: List<Brand>) {
        rlv_brand.layoutManager = GridLayoutManager(this,2)
        val brandListAdapter = BrandListAdapter(this,brandList)
        rlv_brand.adapter = brandListAdapter
    }

    //新品首发
    private fun showNewList(newGoodsList: List<NewGoods>) {
        rlv_new.layoutManager = GridLayoutManager(this,2)
        val newListAdapter = NewListAdapter(this,newGoodsList)
        rlv_new.adapter = newListAdapter
    }

    //人气推荐
    private fun showHotList(hotGoodsList: List<HotGoods>) {
        rlv_hot.layoutManager = LinearLayoutManager(this)
        val hotListAdapter = HotListAdapter(this,hotGoodsList)
        rlv_hot.adapter = hotListAdapter
    }

    //专题推荐
    private fun showTopic(topicList: List<Topic>) {
        rlv_topic.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        val topicAdapter = TopicAdapter(this,topicList)
        rlv_topic.adapter = topicAdapter
    }

    //自定义的
    private fun showCategory(categoryList: List<Category>) {
        for (i in categoryList.indices){
            val view : View = LayoutInflater.from(this).inflate(R.layout.home_item_sss,null)
            liner.addView(view)
            Log.e("111", "showCategory: "+i )
            val text = view.findViewById<TextView>(R.id.txt_home_title)
            text.text = (categoryList[i].name)


            val goodsList =  categoryList[i].goodsList
            val recy_home: RecyclerView = view.findViewById(R.id.recy_home)
            recy_home.layoutManager = GridLayoutManager(this,2)
            val categoryListAdapter = categoryListAdapter(this,goodsList)
            recy_home.adapter = categoryListAdapter
        }
    }

}