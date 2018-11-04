package com.kaloglu.duels.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import com.kaloglu.duels.data.cache.DuelsDb
import com.kaloglu.duels.data.cache.UserDao
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {

    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun provideGithubDb(app: Application): DuelsDb {
            return Room
                    .databaseBuilder(app, DuelsDb::class.java, "github.db")
                    .fallbackToDestructiveMigration()
                    .build()
        }

        @JvmStatic
        @PerApplication
        @Provides
        fun provideUserDao(db: DuelsDb): UserDao {
            return db.userDao()
        }
    }
}
