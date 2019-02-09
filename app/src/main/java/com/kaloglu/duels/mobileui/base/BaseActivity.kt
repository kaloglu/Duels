package com.kaloglu.duels.mobileui.base

import android.os.Bundle
import androidx.annotation.UiThread
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    protected abstract val contentResourceId: Int

    protected abstract val baseFrameLayoutId: Int

    protected abstract val snackbarLayoutId: Int

    protected abstract val containedFragment: BaseFragment?
    protected abstract var currentFragment: BaseFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()

        initUserInterface()

    }

    protected abstract fun initUserInterface()

    protected open fun setContentView() = setContentView(contentResourceId)

    @UiThread
    fun showSnackbar(messageId: Int) = showSnackbar(resources.getString(messageId))

    @UiThread
    fun showSnackbar(message: String) = Snackbar.make(findViewById(snackbarLayoutId), message, Snackbar.LENGTH_LONG).show()

    @UiThread
    fun fetchColor(color: Int) = ContextCompat.getColor(this, color)

}
