package com.kaloglu.duels.data.model

import com.google.firebase.firestore.IgnoreExtraProperties

import com.kaloglu.duels.utils.empty

/**
 * Tournament POJO.
 */
@IgnoreExtraProperties
data class Tournament constructor(
        val name: String? = String.empty
) : BaseModel() {

    companion object {

        const val FIELD_NAME = "name"
    }
}
