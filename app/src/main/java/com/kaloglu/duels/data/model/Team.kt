package com.kaloglu.duels.data.model

import com.google.firebase.firestore.IgnoreExtraProperties

import com.kaloglu.duels.utils.empty

/**
 * Player POJO.
 */
@IgnoreExtraProperties
data class Team constructor(
        val name: String? = String.empty
) : BaseModel() {

    companion object {

        const val FIELD_NAME = "name"
    }
}
