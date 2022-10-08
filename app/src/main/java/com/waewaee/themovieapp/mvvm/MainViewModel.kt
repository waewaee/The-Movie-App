package com.waewaee.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl
import com.waewaee.themovieapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.data.vos.MovieVO

class MainViewModel: ViewModel() {
    // Model
    private val mMovieModel: MovieModel = MovieModelImpl

    // Live Data
    var nowPlayingMoviesLiveData: LiveData<List<MovieVO>>? = null
    var popularMoviesLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMoviesLiveData: LiveData<List<MovieVO>>? = null
    val genresLiveData =  MutableLiveData<List<GenreVO>>()
    val moviesByGenreLiveData = MutableLiveData<List<MovieVO>>()
    val actorsLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()


    fun getInitialData() {
        nowPlayingMoviesLiveData = mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMoviesLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }
        topRatedMoviesLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }


        mMovieModel.getGenres(
            onSuccess = {
                genresLiveData.postValue(it)
                getMovieByGenre(0)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                actorsLiveData.postValue(it)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }

    fun getMovieByGenre(genrePosition: Int) {
        genresLiveData.value?.getOrNull(genrePosition)?.id?.let {
            mMovieModel.getMoviesByGenre(
                it.toString(),
                onSuccess = { moviesByGenre ->
                    moviesByGenreLiveData.postValue(moviesByGenre)
                },
                onFailure = { errorMessage->
                    mErrorLiveData.postValue(errorMessage)
                })
        }
    }

}