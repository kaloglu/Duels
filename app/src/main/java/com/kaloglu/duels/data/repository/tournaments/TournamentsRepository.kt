package com.kaloglu.duels.data.repository.tournaments

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.domain.FireStoreLiveData
import com.kaloglu.duels.injection.scopes.PerApplication
import com.kaloglu.duels.data.filters.Filters
import timber.log.Timber
import javax.inject.Inject

@PerApplication
class TournamentsRepository @Inject constructor(private val firestore: FirebaseFirestore) {

    fun getTournaments(filters: Filters?): FireStoreLiveData<Tournament> =
            FireStoreLiveData(toQuery(filters), Tournament::class.java)

    private fun toQuery(filters: Filters?): Query {
        // Construct query basic query
        var query: Query = firestore.collection("tournaments")
        query.orderBy(Tournament.FIELD_NAME, Query.Direction.ASCENDING)

        filters?.run {
            // Name (equality filter)
            name?.let {
                query = query.whereEqualTo(Tournament.FIELD_NAME, it)
            }

        }

        /* query could be limited like: query.limit(5) */
        return query
    }

    fun addTournament(tournament: Tournament/*, collabrators:List<Collabrator>*/) {
        val batch = firestore.batch()
        val restRef = firestore.collection("tournaments").document()

        // Add restaurant
        batch.set(restRef, tournament)
//        batch.set(restRef.collection("collabrators").document(), collabrators)

        batch.commit().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Timber.d("Write batch succeeded.")
            } else {
                Timber.w("write batch failed.", task.exception)
            }
        }
    }

    fun removeTournament(id: String) {
        firestore.collection("tournaments").document(id).delete()
    }
}

