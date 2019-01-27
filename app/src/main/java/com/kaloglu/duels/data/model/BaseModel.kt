package com.kaloglu.duels.data.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.kaloglu.duels.utils.empty

/**
 * A Base BaseModel to be extended by other models to add ids.
 */

@Suppress("UNCHECKED_CAST")
@IgnoreExtraProperties
open class BaseModel(
        @Exclude var id: String? = String.empty
) {

    fun <T : BaseModel> withId(id: String): T =
            BaseModel(id) as T
}
