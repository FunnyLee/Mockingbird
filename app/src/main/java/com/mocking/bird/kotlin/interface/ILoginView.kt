package com.mocking.bird.kotlin.`interface`

import com.mocking.bird.kotlin.Entity.LoginInfo

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is ILoginView
 */
interface ILoginView {

    fun loginSuccess(loginInfo: LoginInfo?)

    fun loginFailure(errorMsg: String?)

}