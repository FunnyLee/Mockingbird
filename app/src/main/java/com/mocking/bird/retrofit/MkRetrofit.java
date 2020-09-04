package com.mocking.bird.retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Author: Funny
 * Time: 2020/9/4
 * Description: This is MkRetrofit
 */
public class MkRetrofit {

    public HttpUrl mBaseUrl;
    public Call.Factory mCallFactory;

    //用于缓存Method和serviceMethod
    private Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();

    public MkRetrofit(HttpUrl url, Call.Factory callFactory) {
        this.mBaseUrl = url;
        this.mCallFactory = callFactory;
    }

    public <T> T create(Class<T> service) {
        //动态代理模式
        T t = (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ServiceMethod serviceMethod = loadServiceMethod(method);
                Call call = serviceMethod.invoke(args);
                return call;
            }
        });
        return t;
    }

    public ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod serviceMethod = serviceMethodCache.get(method);
        if (serviceMethod != null) {
            return serviceMethod;
        } else {
            return new ServiceMethod.Builder(this, method).build();
        }
    }

    //构建者模式
    public static class Builder {

        private Call.Factory callFactory;
        private HttpUrl url;

        public Builder baseUrl(String baseUrl) {
            this.url = HttpUrl.get(baseUrl);
            return this;
        }

        //可以设置自定义的OkHttpClient
        public Builder callFactory(Call.Factory callFactory) {
            this.callFactory = callFactory;
            return this;
        }

        public MkRetrofit build() {
            //非空校验
            if (url == null) {
                throw new IllegalArgumentException("url is required");
            }

            //如果为空，则设置一个默认的OkHttpClient
            if (callFactory == null) {
                callFactory = new OkHttpClient();
            }

            return new MkRetrofit(url, callFactory);
        }
    }


}
