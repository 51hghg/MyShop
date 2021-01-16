package com.shop.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shop.R
import com.shop.ui.login.Result
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class BaseA : AppCompatActivity() {

    //var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home);
       /* binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding!!.btnOpen.setOnClickListener {

        }*/

        //创建一个协程
        MainScope().launch {
            getHome()
        }

        testExt()


    }

    fun testExt(){
        var user = User("小明", 20)
    }



    //suspend 定义的方法  suspend：申明这是个可挂起的函数，里面可以用协程的方法
    suspend fun getHome(){
        val result = get("https://cdplay.cn/api/index")
        Log.e("TAG",result.toString());
    }
    // withContext：主线程安全，用来切换线程，并返回执行后的结果   IO线程
    suspend fun get(net:String) = withContext(Dispatchers.IO){
        val url = URL(net)
        //                              强转
        var httpConnect = url.openConnection() as? HttpURLConnection
        httpConnect.run {

        }
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"   //请求方式
            setRequestProperty("Content-Type","application/json;utf-8")  //设置请求头
            setRequestProperty("Accept","application/json")
            Log.d("TAG", responseCode.toString())
            //定义接收数据的sb
            var data = StringBuffer()
            // InputStreamReader 读取httpurlconnection拿到的数据
            val streamReader = InputStreamReader(this.inputStream,"utf-8")
            val reader = BufferedReader(streamReader)  // new BufferedReader(streamReader);  -> java
            // while() -> java
            reader.use {
                var temp = it.readLine()  //读取数据的一行
                if(temp != null) data.append(temp)
            }
            reader.close()
            streamReader.close()
            inputStream.close()
            return@run Result.Success(data)  //
        }
        //return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
    }

    suspend fun fetchDownDoc() =
            coroutineScope {
                val one = async { getHome() }
                var two = async { }
            }
}