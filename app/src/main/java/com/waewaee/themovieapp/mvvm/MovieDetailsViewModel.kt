package com.waewaee.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl
import com.waewaee.themovieapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.MovieVO

class MovieDetailsViewModel: ViewModel() {
    // Model
    private val mMovieModel: MovieModel = MovieModelImpl

    // Live Data
    var movieDetailsLiveData: LiveData<MovieVO?>? = null
    val castLiveData = MutableLiveData<List<ActorVO>>()
    val crewLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(movieId: Int) {
        movieDetailsLiveData =
            mMovieModel.getMovieDetails(movieId = movieId.toString()) { mErrorLiveData.postValue(it) }

        mMovieModel.getCreditsByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                castLiveData.postValue(it.first ?: listOf())
                crewLiveData.postValue(it.second ?: listOf())
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }
}