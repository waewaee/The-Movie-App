package com.waewaee.themovieapp.data.models

import com.waewaee.themovieapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.network.dataagents.MovieDataAgent
import com.waewaee.themovieapp.network.dataagents.RetrofitDataAgentImpl

object MovieModelImpl: MovieModel {

    val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getPopularMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getPopularMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getTopRatedMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getGenres(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMoviesByGenre(
            genreId = genreId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getActors(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMovieDetails(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCreditsByMovie(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}