package com.mocking.bird.kotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is BaseActivity
 */
abstract class BaseActivity<P> : AppCompatActivity() where P : IBasePresenter {

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        presenter = createPresenter()
        initView()
        initData()
        initEvent()
    }

    abstract fun getLayoutId(): Int

    abstract fun initEvent()

    abstract fun initData()

    abstract fun initView()

    abstract fun createPresenter(): P
}