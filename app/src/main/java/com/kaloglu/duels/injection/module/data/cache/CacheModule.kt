package com.kaloglu.duels.injection.module.data.cache

import android.app.Application
import androidx.room.Room
import com.kaloglu.duels.data.cache.db.DuelsDb
import com.kaloglu.duels.data.cache.sample.SampleDao
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun provideDb(app: Application): DuelsDb {
            return Room
                    .databaseBuilder(app, DuelsDb::class.java, "DBName")
                    .fallbackToDestructiveMigration()
                    .build()
        }

        @JvmStatic
        @PerApplication
        @Provides
        fun provideUserDao(db: DuelsDb): SampleDao {
            return db.sampleDao()
        }
    }
}
