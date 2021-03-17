package com.mocking.bird.kotlin.Entity

import com.google.gson.annotations.SerializedName

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is BaseInfo
 */
data class BaseInfo<T>(
        @SerializedName("errorCode")
        val errorCode: Int,
        @SerializedName("errorMsg")
        val errorMsg: String,
        @SerializedName("data")
        val data: T
) {}