package com.kaloglu.duels.domain.model

import com.google.firebase.firestore.IgnoreExtraProperties
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.utils.extensions.empty

/**
 * Tournament POJO.
 */
@IgnoreExtraProperties
data class Tournament @JvmOverloads constructor(
        override var name: String = String.empty,
        var players: List<Player> = emptyList(),
        var teams: List<Team> = emptyList()
) : BaseModel()
