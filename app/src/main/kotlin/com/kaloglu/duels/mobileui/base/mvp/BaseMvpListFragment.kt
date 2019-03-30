package com.kaloglu.duels.mobileui.base.mvp

import android.view.View
import androidx.annotation.CallSuper
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.mobileui.interfaces.UIStateType
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpListView
import com.kaloglu.duels.presentation.interfaces.tournament.Model

abstract class BaseMvpListFragment<M : BaseModel, V : MvpListView<M>, P : MvpListPresenter<M, V>>
    : BaseMvpFragment<V, P>(), MvpListView<M> {

    override fun onLoading() = presenter.getUIState(UIStateType.LOADING)

    override fun onEmpty() = presenter.getUIState(UIStateType.EMPTY)

    @CallSuper
    override fun onSuccess(data: List<M>) = presenter.getUIState(UIStateType.CONTENT)

    @CallSuper
    override fun onError(errorMessage: String?, data: List<M>?) =
            presenter.getUIState(UIStateType.ERROR)

    @CallSuper
    override fun onClickItem(model: M) = presenter.openDetail(model)

    @CallSuper
    override fun onClickView(model: M, view: View) = Unit

    override fun showBottomMenu(model: Model, bottomSheetLayout: Int) {
        BottomSheetDialog(view!!.context).run {
            setContentView(
                    layoutInflater.inflate(bottomSheetLayout, null)
            )
            show()
        }
    }

    @CallSuper
    override fun onPresenterAttached() {
        super.onPresenterAttached()
        presenter.observe()
    }
}
