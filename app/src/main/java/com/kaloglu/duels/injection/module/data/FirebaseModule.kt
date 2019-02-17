package com.kaloglu.duels.injection.module.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.kaloglu.duels.domain.TableNames
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class FirebaseModule {

    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun firestore(): FirebaseFirestore {
            FirebaseFirestore.setLoggingEnabled(true)
            val fireStoreDb = FirebaseFirestore.getInstance()
            fireStoreDb.firestoreSettings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build()
            return fireStoreDb
        }

        @JvmStatic
        @PerApplication
        @Provides
        @Named(TableNames.TOURNAMENTS)
        fun tournamentsCollection(firestore: FirebaseFirestore) =
                firestore.collection(TableNames.TOURNAMENTS)
    }

}