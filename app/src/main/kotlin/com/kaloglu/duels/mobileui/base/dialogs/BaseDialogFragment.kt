package com.kaloglu.duels.mobileui.base.dialogs

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDialogFragment

abstract class BaseDialogFragment : AppCompatDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStyle()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dialogView = inflateView(inflater, container)
        return dialogView
    }

    open fun inflateView(layoutInflater: LayoutInflater, container: ViewGroup?): View =
            layoutInflater.inflate(getResourceLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserInterface(view)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        onDialogWindow(dialog.window!!)
        return dialog
    }
    open fun initStyle() = Unit //setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Style_Dialog)

    open fun onDialogWindow(window: Window) {
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window.setBackgroundDrawable(ColorDrawable(0))
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.attributes.dimAmount = 0.9f
//        window.attributes.windowAnimations = R.style.DialogSlide
    }

    /**
     * Get fragment's UI content layout resource id.
     *
     * @return The fragment's root view's resource id
     */
    protected abstract fun getResourceLayoutId(): Int

    /**
     * Initialize UI content elements.
     *
     * @param dialogView The dialog's root view
     */
    protected abstract fun initUserInterface(dialogView: View)
}
