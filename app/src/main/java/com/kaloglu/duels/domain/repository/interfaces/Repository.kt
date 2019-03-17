package com.kaloglu.duels.domain.repository.interfaces

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.kaloglu.duels.domain.filters.Filters
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.domain.FireStoreLiveList

interface Repository<M : BaseModel> {

    fun getModelClass(): Class<M>
    fun get(filters: Filters?): FireStoreLiveList<M>
    fun toQuery(filters: Filters?): Query
    fun add(model: M): Task<Void>
    fun remove(id: String): Task<Void>

}
