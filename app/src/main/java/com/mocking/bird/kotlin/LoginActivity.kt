package com.mocking.bird.kotlin

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.mocking.bird.R
import com.mocking.bird.kotlin.Entity.LoginInfo
import com.mocking.bird.kotlin.`interface`.ILoginView
import com.mocking.bird.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is LoginActivity
 */
class LoginActivity : BaseActivity<LoginPresenter>(), ILoginView {

    companion object {
        fun starter(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {
        login_btn.setOnClickListener {
            val user = user_et.text.toString().trim()
            val pwd = password_et.text.toString().trim()
            presenter.loginAction(user, pwd)
        }
    }

    override fun loginSuccess(loginInfo: LoginInfo?) {
        Toast.makeText(this, loginInfo?.publicName, Toast.LENGTH_SHORT).show()
    }

    override fun loginFailure(errorMsg: String?) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }

}