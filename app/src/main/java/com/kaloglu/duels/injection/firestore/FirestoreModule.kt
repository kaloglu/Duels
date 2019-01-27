package com.kaloglu.duels.injection.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named
import com.google.firebase.firestore.FirebaseFirestoreSettings

@Module
internal class FirestoreModule {

    @PerApplication
    @Provides
    fun providesFirestore(): FirebaseFirestore {
        FirebaseFirestore.setLoggingEnabled(true)
        val fireStoreDb = FirebaseFirestore.getInstance()
        fireStoreDb.firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
        return fireStoreDb
    }

    @PerApplication
    @Provides
    @Named("tournaments")
    fun providesTournaments(fireStoreDb: FirebaseFirestore): CollectionReference {
        return fireStoreDb.collection("tournaments")
    }

}
