package com.kaloglu.duels.data.model

import com.google.firebase.firestore.IgnoreExtraProperties

import com.kaloglu.duels.utils.empty
import java.io.Serializable

/**
 * Tournament POJO.
 */
@IgnoreExtraProperties
data class Tournament constructor(
        val name: String? = String.empty
) : BaseModel(), Serializable {
    val players: List<Player> = emptyList()
    val teams: List<Team> = emptyList()

    companion object {

        const val FIELD_NAME = "name"
    }
}
