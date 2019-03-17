package com.kaloglu.duels.domain.filters

import com.google.firebase.firestore.Query

/**
 * Object for passing filters around.
 */
abstract class Filters @JvmOverloads constructor(
        var equalToMap: MutableMap<String, Any> = mutableMapOf(),
        var sortMap: MutableMap<String, Query.Direction> = mutableMapOf()
) {
    fun addEqualTo(field: String, value: Any) {
        equalToMap[field] = value
    }

    @JvmOverloads
    fun addSort(field: String, direction: Query.Direction = Query.Direction.ASCENDING) {
        sortMap[field] = direction
    }
}
