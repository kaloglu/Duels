package com.kaloglu.duels.presentation.interfaces.base.bottomsheetmenu

import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kaloglu.duels.R
import com.kaloglu.duels.domain.model.base.BaseModel

interface BottomSheetMenuView<in M : BaseModel> {
    val layoutId: Int
        get() = R.layout.bottom_sheet_dialog

    var bottomSheetDialog: BottomSheetDialog

    var bottomSheetView: View

    fun initBottomMenuView(viewGroup: ViewGroup)

    fun onClickBottomMenuItem(bottomSheetItem: M, bottomMenuItemView: View)
    fun show(itemModel: M)
    fun init(viewGroup: ViewGroup)
}
