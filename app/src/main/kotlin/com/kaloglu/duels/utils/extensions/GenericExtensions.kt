@file:JvmName("Utility")

package com.kaloglu.duels.utils.extensions

import androidx.lifecycle.Observer
import com.kaloglu.duels.domain.FireStoreLiveList
import com.kaloglu.duels.domain.enums.Status
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.presentation.interfaces.base.mvp.ResponseLiveListView

/**
 * Created by kaloglu on 6.01.2019.
 */

internal val String.Companion.empty
    get() = ""

fun <T : Any> Class<T>.checkInjection(any: T?) =
        checkNotNull(any) {
            val firstChar = simpleName.first()
            val firstCharLowerCase = simpleName.replaceFirst(firstChar, firstChar.toLowerCase())
            "you should add \"$firstCharLowerCase: $simpleName\" to providing ListPresenter method at Module"
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
