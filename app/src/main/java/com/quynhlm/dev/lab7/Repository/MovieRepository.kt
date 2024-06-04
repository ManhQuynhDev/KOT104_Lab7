package com.quynhlm.dev.lab7.Repository

import android.app.Application
import android.app.LocaleConfig
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import com.quynhlm.dev.lab7.Dao.MovieDao
import com.quynhlm.dev.lab7.Database.MovieDatabase
import com.quynhlm.dev.lab7.Models.Movie

class MovieRepository (app: Application){
    private val movieDao : MovieDao

    init {
        val movieDatabase : MovieDatabase = MovieDatabase.getInstance(app)
        movieDao = movieDatabase.getMovieDao()
    }

    suspend fun insert(movies: List<Movie>) = movieDao.insert(movies)
    fun getAllNote() : LiveData<List<Movie>> = movieDao.getAllData()
}