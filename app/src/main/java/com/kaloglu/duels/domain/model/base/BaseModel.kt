package com.kaloglu.duels.domain.model.base

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.kaloglu.duels.utils.extensions.empty
import java.io.Serializable

/**
 * A Base BaseModel to be extended by other models to add ids.
 */

@Suppress("UNCHECKED_CAST")
@IgnoreExtraProperties
abstract class BaseModel @JvmOverloads constructor(
        @Exclude var id: String = String.empty,
        open var name: String = String.empty
) : Serializable {
    companion object {
        const val serialVersionUID = 1L
    }
}
