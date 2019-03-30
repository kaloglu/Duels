package com.kaloglu.duels.presentation.interfaces.base.mvp

import androidx.annotation.LayoutRes
import com.kaloglu.duels.R
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.interfaces.tournament.Model

interface MvpListView<M : BaseModel> : ResponseLiveListView<M>, UIStateManager.UIStatesView {
    fun onClickView(model: M, view: android.view.View)
    fun onClickItem(model: M)
    fun showBottomMenu(model: Model, @LayoutRes bottomSheetLayout: Int = R.layout.bottom_sheet_dialog)
}
