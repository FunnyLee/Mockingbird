package com.mocking.bird.retrofit;

/**
 * Author: Funny
 * Time: 2020/9/4
 * Description: This is 处理请求参数
 */
public class ParamHandler {

    //请求参数的key，从请求参数的注解上解析出来
    public String key;

    public ParamHandler(String key) {
        this.key = key;
    }

    //设置请求参数的value
    public void apply(ServiceMethod serviceMethod, String value) {
        serviceMethod.addQueryParam(key, value);
    }
}

