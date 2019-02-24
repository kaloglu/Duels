package com.kaloglu.duels.data.filters

import com.google.firebase.firestore.Query

/**
 * Object for passing filters around.
 */
class Filters {

    var filterByEqualTo: Map<String, *>? = null
    var sortDirection: Query.Direction? = null
    var sortBy: String? = null
    val name: String? = null

    companion object {

        val default: Filters
            get() {
                val filters = Filters()
                filters.sortDirection = Query.Direction.ASCENDING

                return filters
            }
    }
}
