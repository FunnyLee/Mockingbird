package com.mocking.bird.kotlin.net

import com.mocking.bird.kotlin.Entity.BaseInfo
import com.mocking.bird.kotlin.Entity.LoginInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is WanAndroidApi
 */
interface WanAndroidApi {

    @POST("user/login")
    @FormUrlEncoded
    fun register(@Field("username") username: String,
                 @Field("password") pwd: String,
                 @Field("repassword") rePwd: String): Observable<BaseInfo<LoginInfo>>

}