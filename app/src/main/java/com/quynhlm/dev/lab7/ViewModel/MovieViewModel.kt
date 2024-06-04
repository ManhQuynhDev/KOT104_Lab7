package com.quynhlm.dev.lab7.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.quynhlm.dev.lab7.Models.Movie
import com.quynhlm.dev.lab7.Repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : ViewModel() {


    private val movieRepository : MovieRepository = MovieRepository(application)

    fun insertNote(movies: List<Movie>) = viewModelScope.launch {
        movieRepository.insert(movies)
    }

    fun getAllNote() : LiveData<List<Movie>> = movieRepository.getAllNote()

    class MovieViewModelFactory(private var application: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
                return MovieViewModel(application) as T
            }
            throw  IllegalArgumentException("Unable construct viewModel")
        }
    }
}