package com.kaloglu.duels.data.repository.tournament

import com.google.firebase.firestore.CollectionReference
import com.kaloglu.duels.domain.TableNames
import com.kaloglu.duels.domain.model.Tournament
import com.kaloglu.duels.domain.repository.base.BaseRepository
import com.kaloglu.duels.injection.scopes.PerApplication
import javax.inject.Inject
import javax.inject.Named

@PerApplication
class TournamentRepository @Inject constructor(
        @Named(TableNames.TOURNAMENT_LIST)
        override val collectionRef: CollectionReference
) : BaseRepository<Tournament>() {

    override fun getModelClass() = Tournament::class.java

}
