package com.mocking.bird.retrofit;

import com.mocking.bird.retrofit.annotation.Query;
import com.mocking.bird.retrofit.annotation.GET;

import okhttp3.Call;

/**
 * Author: Funny
 * Time: 2020/9/4
 * Description: This is WeatherApi
 */
public interface WeatherApi {

    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);

}
