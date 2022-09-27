package com.waewaee.themovieapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.waewaee.themovieapp.data.vos.ActorVO

@Dao
interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActors(actors: List<ActorVO>)

    @Query("SELECT * FROM actors")
    fun getAllActors() : List<ActorVO>

    @Query("SELECT * FROM actors WHERE movieId = :movieId")
    fun getActorsByMovieId(movieId: Int) : List<ActorVO>

    @Query("SELECT * FROM actors WHERE type = :type")
    fun getActorsByType(type: String) : List<ActorVO>

    @Query("DELETE FROM actors")
    fun deleteAllActors()
}