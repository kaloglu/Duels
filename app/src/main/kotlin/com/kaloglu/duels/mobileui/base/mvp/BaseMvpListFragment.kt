package com.kaloglu.duels.mobileui.base.mvp

import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.kaloglu.duels.R
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.mobileui.interfaces.UIStateType
import com.kaloglu.duels.presentation.interfaces.base.bottomsheetmenu.BottomSheetMenu
import com.kaloglu.duels.presentation.interfaces.base.bottomsheetmenu.BottomSheetMenuView
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpListView

abstract class BaseMvpListFragment<M : BaseModel, V : MvpListView<M>, P : MvpListPresenter<M, V>>
    : BaseMvpFragment<V, P>(), MvpListView<M> {

    override var bottomSheetMenuView: BottomSheetMenuView<M> = object : BottomSheetMenu<M>() {
        override fun onClickBottomMenuItem(
                bottomSheetItem: M,
                bottomMenuItemView: View
        ) {
            when (bottomMenuItemView.id) {
                R.id.bottomSheetEdit -> presenter.openDetail(bottomSheetItem)
                R.id.bottomSheetDelete -> presenter.remove(bottomSheetItem)
                else -> TODO("should override onClickBottomMenuItem(bottomMenuItem: View)")
            }

        }
    }

    override fun onLoading() = presenter.getUIState(UIStateType.LOADING)

    override fun onEmpty() = presenter.getUIState(UIStateType.EMPTY)

    @CallSuper
    override fun onSuccess(data: List<M>) = presenter.getUIState(UIStateType.CONTENT)

    @CallSuper
    override fun onError(errorMessage: String?, data: List<M>?) =
            presenter.getUIState(UIStateType.ERROR)

    override fun initUserInterface(rootView: View) {
        super.initUserInterface(rootView)
        bottomSheetMenuView.init(rootView as ViewGroup)
    }

    override fun onClickItem(model: M) = presenter.openDetail(model)

    override fun onClickView(model: M, view: View) = Unit

    @CallSuper
    override fun onPresenterAttached() {
        super.onPresenterAttached()
        presenter.observe()
    }

}
