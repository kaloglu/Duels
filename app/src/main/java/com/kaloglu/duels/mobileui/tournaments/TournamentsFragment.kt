package com.kaloglu.duels.mobileui.tournaments

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.duels.presentation.interfaces.tournaments.TournamentsContract
import kotlinx.android.synthetic.main.fragment_tournaments.*
import kotlinx.android.synthetic.main.tournaments_content.*
import kotlinx.android.synthetic.main.tournaments_empty.*
import kotlinx.android.synthetic.main.tournaments_loading.*

class TournamentsFragment
    : BaseMvpFragment<Any, TournamentsContract.Presenter>()
        , TournamentsContract.View<Any> {

    override val resourceLayoutId = R.layout.fragment_tournaments
    override fun getSceneLayout(): ConstraintLayout? = tournamentsScene
    override fun getLoadingContainer(): View = loading
    override fun getEmptyContainer(): View = empty
    override fun getContentContainer(): View = content
    override fun getErrorContainer(): View? = null

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
        presenter.contentUIState()
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
