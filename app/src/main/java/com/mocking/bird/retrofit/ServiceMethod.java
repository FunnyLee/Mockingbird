package com.mocking.bird.retrofit;

import com.mocking.bird.retrofit.annotation.GET;
import com.mocking.bird.retrofit.annotation.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Author: Funny
 * Time: 2020/9/4
 * Description: This is 处理请求类型，url，请求参数
 */
public class ServiceMethod {

    public MkRetrofit retrofit;
    public String httpMethod;
    public String relativeUrl;
    public ParamHandler[] mParamHandlerArray;

    public HttpUrl.Builder httpUrlBuilder;
    public HttpUrl baseUrl;


    public ServiceMethod(Builder builder) {
        //这个builder中已经有了一系列信息
        retrofit = builder.retrofit;
        baseUrl = retrofit.mBaseUrl;
        httpMethod = builder.httpMethod;
        relativeUrl = builder.relativeUrl;
        mParamHandlerArray = builder.mParamHandlerArray;
    }

    /**
     * 真正执行网络请求的地方
     *
     * @param args 方法里传的请求参数的值
     */
    public Call invoke(Object[] args) {
        //请求的完整url
        if (httpUrlBuilder == null) {
            httpUrlBuilder = baseUrl.newBuilder(relativeUrl);
        }

        //请求参数key-value拼接
        for (int i = 0; i < mParamHandlerArray.length; i++) {
            ParamHandler paramHandler = mParamHandlerArray[i];
            paramHandler.apply(this, args[i].toString());
        }

        Request request = new Request.Builder().url(httpUrlBuilder.build()).method(httpMethod, null).build();
        Call call = retrofit.mCallFactory.newCall(request);
        return call;
    }

    public void addQueryParam(String key, String value) {
        httpUrlBuilder.addQueryParameter(key, value);
    }

    /**
     * 内部主要处理方法上的注解，得到请求方式和relativeUrl；处理方法参数中的注解，得到请求参数的key
     */
    static class Builder {

        //这四个参数，网络请求的时候都要用到
        private MkRetrofit retrofit;
        private String httpMethod;
        private String relativeUrl;
        private ParamHandler[] mParamHandlerArray;

        private Annotation[] annotations;
        private Annotation[][] paramAnnotations;

        public Builder(MkRetrofit retrofit, Method method) {
            this.retrofit = retrofit;
            //获取方法上的注解，一个方法上可能有多个注解，所以是一个数组，这里我们只关注GET和POST注解
            annotations = method.getAnnotations();
            //获取方法参数中的注解，一个方法可能有多个参数，一个参数可能有多个注解，所以是一个二维数组
            paramAnnotations = method.getParameterAnnotations();
        }

        public ServiceMethod build() {
            //解析方法上的注解，给请求方法和relativeUrl赋值
            for (Annotation annotation : annotations) {
                if (annotation instanceof GET) {
                    httpMethod = "GET";
                    relativeUrl = ((GET) annotation).value();
                }
            }

            //解析方法参数中的注解，得到请求参数的key，设置给ParamHandler
            mParamHandlerArray = new ParamHandler[paramAnnotations.length];
            for (int i = 0; i < paramAnnotations.length; i++) {
                //一个参数上的所有注解，这里只关注QUERY注解
                Annotation[] annotation = paramAnnotations[i];
                for (Annotation anno : annotation) {
                    if (anno instanceof Query) {
                        String value = ((Query) anno).value();
                        //这里只是获取到了请求参数的key
                        mParamHandlerArray[i] = new ParamHandler(value);
                    }
                }
            }

            return new ServiceMethod(this);
        }
    }
}
