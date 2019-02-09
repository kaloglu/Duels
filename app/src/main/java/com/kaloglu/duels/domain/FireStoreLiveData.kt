package com.kaloglu.duels.domain

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import com.kaloglu.duels.data.model.BaseModel
import com.kaloglu.duels.presentation.interfaces.base.mvp.ResponseLiveDataView
import com.kaloglu.duels.viewobjects.Resource
import com.kaloglu.duels.viewobjects.Status

class FireStoreLiveData<T : BaseModel>(
        private val query: Query,
        private val type: Class<T>
) : LiveData<Resource<List<T>>>(), EventListener<QuerySnapshot> {
    private var registration: ListenerRegistration? = null

    override fun onEvent(snapshots: QuerySnapshot?, e: FirebaseFirestoreException?) {
        if (e != null) {
            value = Resource.error(e.localizedMessage, documentToList(snapshots))
            return
        }
        value = Resource.success(documentToList(snapshots) ?: listOf())
    }

    override fun onActive() {
        super.onActive()
        value = Resource.loading()
        registration = query.addSnapshotListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        if (registration != null) {
            registration!!.remove()
            registration = null
        }
    }

    private fun documentToList(snapshots: QuerySnapshot?): List<T>? =
            snapshots
                    ?.takeIf { !it.isEmpty }
                    ?.documents
                    ?.mapTo(mutableListOf<T>()) {
                        it.toObject(type)!!.withId(it.id)
                    }
                    ?.toList()

    @Suppress("UNCHECKED_CAST")
    fun <T> observe(view: ResponseLiveDataView<T>) {
        observe(view, androidx.lifecycle.Observer {
            when (it?.status) {
                Status.LOADING -> view.onLoading()
                Status.SUCCESS -> view.onSuccess(it.data as T)
                Status.ERROR -> view.onError(it.message, it.data as T)
                null -> TODO("should define ${it?.status}")
            }
        })
    }
}
