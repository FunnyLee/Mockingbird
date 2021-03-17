package com.mocking.bird.kotlin.net

import android.util.Log
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.mocking.bird.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is MainRetrofit
 */
class MainRetrofit private constructor() {

    companion object {

        val base_url = "https://www.wanandroid.com/"

        fun getInstance(): Retrofit {
            //日志拦截器

            //日志拦截器
            val loggingInterceptor = LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Request")
                    .response("Response")
                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .addQueryParam("query", "0")
                    .enableAndroidStudio_v3_LogsHack(true) /* enable fix for logCat logging issues with pretty format */
                    .logger { level, tag, msg -> Log.w(tag, msg) }
                    .executor(Executors.newSingleThreadExecutor())
                    .build()

            val okHttpClient = OkHttpClient().newBuilder()
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build()

            var retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            return retrofit
        }

    }
}