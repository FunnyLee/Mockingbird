package com.mocking.bird.kotlin.`interface`

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is ILonginModel
 */
interface ILoginModel {

    fun loginAction(userName: String, password: String, listener: ILoginPresenter.OnLoginListener)

}