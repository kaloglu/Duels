package com.kaloglu.duels.mobileui.tournaments

import android.os.Handler
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.duels.presentation.interfaces.tournaments.TournamentsContract
import kotlinx.android.synthetic.main.fragment_tournaments.*

class TournamentsFragment
    : BaseMvpFragment<Any, TournamentsContract.Presenter>()
        , TournamentsContract.View<Any> {

    override val loadingLayout: Int = R.layout.tournaments_loading
    override val emptyLayout: Int = R.layout.tournaments_empty
    override val contentLayout: Int = R.layout.tournaments_content
    override val errorLayout: Int? = null

    override val resourceLayoutId = R.layout.fragment_tournaments
    override fun getSceneLayout(): ConstraintLayout = tournamentsScene

    override fun initUserInterface(rootView: View) {

    }


    override fun showSnackbar(messageId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSnackbar(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoading() {
        presenter.loadingUIState()
    }

    override fun onSuccess(data: Any?) {
        presenter.emptyUIState()
//        presenter.contentUIState()
    }

    override fun onError(errorMessage: String?, data: Any?) {
        presenter.errorUIState()
    }

    override fun onPresenterAttached() {
        super.onPresenterAttached()
        presenter.emptyUIState()
    }

    companion object {
        fun newInstance() =
                TournamentsFragment()
    }

}
