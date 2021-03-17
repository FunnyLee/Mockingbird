package com.mocking.bird.kotlin

import com.mocking.bird.kotlin.Entity.BaseInfo
import com.mocking.bird.kotlin.Entity.LoginInfo
import com.mocking.bird.kotlin.`interface`.ILoginModel
import com.mocking.bird.kotlin.`interface`.ILoginPresenter
import com.mocking.bird.kotlin.net.MainRetrofit
import com.mocking.bird.kotlin.net.WanAndroidApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is LoginModel
 */
class LoginModel : ILoginModel {

    override fun loginAction(userName: String, password: String, listener: ILoginPresenter.OnLoginListener) {
        val retrofit = MainRetrofit.getInstance()
        retrofit.create(WanAndroidApi::class.java)
                .register(userName, password, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<BaseInfo<LoginInfo>> {
                    override fun accept(t: BaseInfo<LoginInfo>?) {
                        when (t?.errorCode) {
                            0 -> {
                                //成功
                                listener.loginSuccess(t?.data)
                            }
                            -1 -> {
                                //失败
                                listener.loginFailure(t?.errorMsg)
                            }
                        }
                    }
                }, object : Consumer<Throwable> {
                    override fun accept(t: Throwable?) {
                        //失败
                        listener.loginFailure(t.toString())
                    }
                })
//                .subscribe(object : Observer<BaseInfo<RegisterInfo>> {
//                    override fun onComplete() {
//                    }
//
//                    override fun onSubscribe(d: Disposable?) {
//                    }
//
//                    override fun onNext(t: BaseInfo<RegisterInfo>?) {
//                    }
//
//                    override fun onError(e: Throwable?) {
//                    }
//                })
    }

}


