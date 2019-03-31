package com.kaloglu.duels.presentation.interfaces.base.mvp

import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.interfaces.base.bottomsheetmenu.BottomSheetMenuView

interface MvpListView<M : BaseModel> : ResponseLiveListView<M>, UIStateManager.UIStatesView {
    var bottomSheetMenuView: BottomSheetMenuView<M>

    fun onClickView(model: M, view: android.view.View)
    fun onClickItem(model: M)
}
