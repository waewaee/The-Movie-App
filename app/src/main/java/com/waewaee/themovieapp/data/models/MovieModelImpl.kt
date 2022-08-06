package com.waewaee.themovieapp.data.models

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
}