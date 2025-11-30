package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.model.Movie


class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "b4bc5c0b4f48e4beae5c03c5289d3aa4"
    // LiveData that contains a list of movies
    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = movieLiveData
    // LiveData that contains an error message
    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData
    // fetch movies from the API
    suspend fun fetchMovies() {
        try {
// get the list of popular movies from the API
            val popularMovies = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (exception: Exception) {
// if an error occurs, post the error message to the
            errorLiveData
            errorLiveData.postValue(
                "An error occurred: ${exception.message}")
        }
    }
}