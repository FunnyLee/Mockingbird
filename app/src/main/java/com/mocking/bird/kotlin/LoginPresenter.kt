package com.mocking.bird.kotlin

import com.mocking.bird.kotlin.Entity.LoginInfo
import com.mocking.bird.kotlin.`interface`.ILoginPresenter
import com.mocking.bird.kotlin.`interface`.ILoginView

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is LoginPresenter
 */
class LoginPresenter(val loginView: ILoginView) : ILoginPresenter {

    val loginModel = LoginModel()

    override fun loginAction(user: String, pwd: String) {
        loginModel.loginAction(user, pwd, object : ILoginPresenter.OnLoginListener {
            override fun loginSuccess(loginInfo: LoginInfo?) {
                loginView.loginSuccess(loginInfo)
            }

            override fun loginFailure(errorMsg: String?) {
                loginView.loginFailure(errorMsg)
            }
        })
    }

    override fun attach() {
        TODO("Not yet implemented")
    }

    override fun detach() {
        TODO("Not yet implemented")
    }

}