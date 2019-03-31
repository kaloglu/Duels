package com.kaloglu.duels.mobileui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    internal lateinit var rootView: View

    /**
     * Get fragment's UI content layout resource id.
     *
     * @return The fragment's root view's resource id
     */
    protected abstract val resourceLayoutId: Int

    /**
     * Finds and returns the frame layout's id which holds the fragment.
     *
     * @return id as int
     */
    val containerFrameLayoutId: Int?
        get() = (view?.parent as ViewGroup).id

    val fragmentTag = this.javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflateView(inflater, container)

    open fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View =
            inflater.inflate(resourceLayoutId, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rootView = view
        super.onViewCreated(view, savedInstanceState)
        initUserInterface(view)
    }

    /**
     * Initialize UI content elements.
     *
     * @param rootView The fragment's root view
     */
    @CallSuper
    protected open fun initUserInterface(rootView: View) = Unit

    /**
     * Refresh when call again
     */
    abstract fun refresh()

    /**
     * Called when a fragment will be animate displayed
     */
    abstract fun enterAnimation()

    /**
     * Called when a fragment will be animate hidden
     */
    abstract fun exitAnimation()

}
