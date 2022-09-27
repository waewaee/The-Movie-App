package com.waewaee.themovieapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.waewaee.themovieapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.persistence.daos.ActorDao
import com.waewaee.themovieapp.persistence.daos.MovieDao

@Database(entities = [MovieVO::class, ActorVO::class], version = 2, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "DB_NAME"

        var dbInstance: MovieDatabase? = null

        fun getDbInstance(context: Context) : MovieDatabase? {
            when(dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
}