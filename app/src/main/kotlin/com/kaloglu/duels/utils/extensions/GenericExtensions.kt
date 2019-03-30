@file:JvmName("Utility")

package com.kaloglu.duels.utils.extensions

import androidx.lifecycle.Observer
import com.kaloglu.duels.domain.FireStoreLiveList
import com.kaloglu.duels.domain.enums.Status
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.domain.repository.base.BaseRepository
import com.kaloglu.duels.presentation.interfaces.base.mvp.ResponseLiveListView

/**
 * Created by kaloglu on 6.01.2019.
 */

internal val String.Companion.empty
    get() = ""

inline fun <reified C : Any> C?.checkInjection() =
        checkNotNull(this) {
            throwProvidingError<C>()
        }

inline fun <reified C : BaseRepository<M>, M : BaseModel> C?.checkInjection() =
        checkNotNull(this) {
            throwProvidingError<C>("ListPresenter")
        }

inline fun <reified C : Any> throwProvidingError(PresenterType: String = "Presenter"): String {
    val simpleClassName = C::class.java.simpleName
    val firstChar = simpleClassName.first()
    val firstCharLowerCase = simpleClassName.replaceFirst(firstChar, firstChar.toLowerCase())
    return "you should add \"$firstCharLowerCase: $simpleClassName\" to providing $PresenterType method at Module"
}

@Suppress("UNCHECKED_CAST")
fun <M : BaseModel, L : List<M>> FireStoreLiveList<M>.observe(responseView: ResponseLiveListView<M>) {
    this.observe(
            responseView,
            Observer {
                when (it?.status) {
                    Status.LOADING -> responseView.onLoading()
                    Status.SUCCESS -> responseView.onSuccess(it.data as L)
                    Status.EMPTY -> responseView.onEmpty()
                    Status.ERROR -> responseView.onError(it.message, it.data as L)
                    null -> TODO("should define ${it?.status}")
                }
            }
    )
}
