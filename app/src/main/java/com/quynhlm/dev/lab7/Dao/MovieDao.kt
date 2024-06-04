package com.quynhlm.dev.lab7.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.quynhlm.dev.lab7.Models.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insert(movies: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getAllData() : LiveData<List<Movie>>
}