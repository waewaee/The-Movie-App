package com.waewaee.themovieapp.interactors

import androidx.lifecycle.LiveData
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl
import com.waewaee.themovieapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.data.vos.MovieVO
import io.reactivex.rxjava3.core.Observable

object MovieIneractorImpl: MovieInteractor {

    // Model
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun getNowPlayingMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getNowPlayingMovies(onFailure)
    }

    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getPopularMovies(onFailure)
    }

    override fun getTopRatedMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getTopRatedMovies(onFailure)
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        return mMovieModel.getGenres(onSuccess, onFailure)
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mMovieModel.getMoviesByGenre(genreId, onSuccess, onFailure)
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        return mMovieModel.getActors(onSuccess, onFailure)
    }

    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>? {
        return mMovieModel.getMovieDetails(movieId, onFailure)
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mMovieModel.getCreditsByMovie(movieId, onSuccess, onFailure)
    }

    override fun searchMovie(query: String): Observable<List<MovieVO>> {
        return mMovieModel.searchMovie(query)
    }
}