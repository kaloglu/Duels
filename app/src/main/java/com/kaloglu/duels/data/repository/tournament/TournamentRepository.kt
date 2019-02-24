package com.kaloglu.duels.data.repository.tournament

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.kaloglu.duels.data.filters.Filters
import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.data.model.withId
import com.kaloglu.duels.domain.FireStoreLiveList
import com.kaloglu.duels.domain.TableNames
import com.kaloglu.duels.injection.scopes.PerApplication
import javax.inject.Inject
import javax.inject.Named


typealias T = Tournament

@PerApplication
class TournamentRepository @Inject constructor(
        private val firestore: FirebaseFirestore,
        @Named(TableNames.TOURNAMENT_LIST) private val tournamentListRef: CollectionReference
) {

    fun getTournamentList(filters: Filters?): FireStoreLiveList<Tournament> =
            FireStoreLiveList(toQuery(filters), Tournament::class.java)

    private fun toQuery(filters: Filters?): Query {
        // Construct query basic query
        var query: Query = firestore.collection("tournamentList")
        query.orderBy(Tournament.FIELD_NAME, Query.Direction.ASCENDING)

        filters?.run {
            // Name (equality filter)
            filterByEqualTo?.entries?.forEach {
                query = query.whereEqualTo(it.key, it.value)
            }

            if (sortBy != null && sortDirection != null) {
                query = query.orderBy(sortBy!!, sortDirection!!)
            }

        }

        /* query could be limited like: query.limit(5) */
        return query
    }

    fun addTournament(tournament: Tournament): Task<Void> {
        val newDocument = tournamentListRef.document()
        return newDocument.set(tournament.withId(newDocument.id))
    }

    fun removeTournament(id: String) =
            tournamentListRef.document(id).delete()
}

