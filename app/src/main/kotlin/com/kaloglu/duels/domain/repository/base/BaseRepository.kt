package com.kaloglu.duels.domain.repository.base

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query
import com.kaloglu.duels.domain.FireStoreLiveList
import com.kaloglu.duels.domain.filters.Filters
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.domain.repository.interfaces.Repository

abstract class BaseRepository<M : BaseModel> : Repository<M> {
    abstract val collectionRef: CollectionReference

    override fun get(filters: Filters?) =
            FireStoreLiveList(toQuery(filters), getModelClass())

    override fun toQuery(filters: Filters?): Query {
        val query: Query = collectionRef

        applyOrderBy(query, filters)

        applyFilter(query, filters)

        applyLimit(query)

        return query
    }

    override fun add(model: M): Task<Void> {
        val newDocument = collectionRef.document()
        model.id = newDocument.id
        return newDocument.set(model)
    }

    override fun remove(id: String) =
            collectionRef.document(id).delete()

    protected open fun applyLimit(query: Query) {
        query.limit(getLimit())
    }

    protected open fun applyOrderBy(query: Query, filters: Filters?) {
        filters?.run {
            sortMap.entries.forEach {
                query.orderBy(it.key, it.value)
            }
        }
    }

    protected open fun applyFilter(query: Query, filters: Filters?) {
        filters?.run {
            equalToMap.entries.forEach {
                query.whereEqualTo(it.key, it.value)
            }

        }
    }

    protected open fun getLimit(): Long = DEFAULT_QUERY_LIMIT

    protected open fun getOrderFieldPath(): FieldPath? = null

    companion object {
        const val DEFAULT_QUERY_LIMIT: Long = 20
    }

}
