package com.kaloglu.duels.mobileui.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    protected abstract val contentResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()

        initUserInterface()

    }

    protected abstract fun initUserInterface()

    protected open fun setContentView() = setContentView(contentResourceId)

}
