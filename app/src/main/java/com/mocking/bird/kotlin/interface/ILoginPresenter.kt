package com.mocking.bird.kotlin.`interface`

import com.mocking.bird.kotlin.Entity.LoginInfo
import com.mocking.bird.kotlin.base.IBasePresenter

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is ILoginPresenter
 */
interface ILoginPresenter : IBasePresenter {

    fun loginAction(user: String, pwd: String)

    interface OnLoginListener {

        fun loginSuccess(loginInfo: LoginInfo?)

        fun loginFailure(errorMsg: String?)
    }

}