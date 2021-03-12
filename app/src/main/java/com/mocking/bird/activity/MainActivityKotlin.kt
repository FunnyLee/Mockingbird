package com.mocking.bird.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mocking.bird.R
import com.mocking.bird.annotation.AnnotationActivity
import com.mocking.bird.kotlin.KotlinActivity
import com.mocking.bird.retrofit.MkRetrofit
import com.mocking.bird.retrofit.WeatherApi
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Author: Funny
 * Time: 2021/3/12
 * Description: This is MainActivityV2
 */
class MainActivityKotlin : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hello_btn.setOnClickListener(this)
        video_btn.setOnClickListener(this)
        kotlin_btn.setOnClickListener(this)

//        initEvent()

        requestWeather()
    }

    fun initEvent(): Unit {
        hello_btn.setOnClickListener { v: View? ->
            val intent = Intent(this, AnnotationActivity::class.java)
            intent.putExtra("name", "mockingbird")
            intent.putExtra("age", 11)
            startActivity(intent)
        }

        video_btn.setOnClickListener {
            VideoActivity.start(this)
        }

        kotlin_btn.setOnClickListener { v ->
            val intent: Intent = Intent(this, KotlinActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.hello_btn -> {
                val intent = Intent(this, AnnotationActivity::class.java)
                intent.putExtra("name", "mockingbird")
                intent.putExtra("age", 11)
                startActivity(intent)
            }
            R.id.video_btn -> {
                VideoActivity.start(this)
            }
            R.id.kotlin_btn -> {
                val intent: Intent = Intent(this, KotlinActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun requestWeather() {
        val mkRetrofit: MkRetrofit = MkRetrofit.Builder().baseUrl("https://restapi.amap.com").build()
        val weatherApi = mkRetrofit.create(WeatherApi::class.java)
        val call: Call = weatherApi.getWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b")

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {

                if (response.code() == 200) {
                    Log.d("Mockingbird", response.body().toString())
                }
            }
        })
    }
}

