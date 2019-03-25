package com.kaloglu.duels.domain

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.viewobjects.Resource

class FireStoreLiveList<T : BaseModel>(
        private val query: Query,
        private val type: Class<T>
) : LiveData<Resource<List<T>>>(), EventListener<QuerySnapshot> {
    private var registration: ListenerRegistration? = null

    override fun onEvent(snapshots: QuerySnapshot?, e: FirebaseFirestoreException?) {
        val listData = documentToList(snapshots)
        value = when {
            e != null -> Resource.error(e.localizedMessage, listData)
            listData.isNullOrEmpty() -> Resource.empty()
            else -> Resource.success(listData)
        }
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
                    ?.map {
                        val toObject = it.toObject(type)!!
                        toObject.id = it.id
                        toObject
                    }
}
