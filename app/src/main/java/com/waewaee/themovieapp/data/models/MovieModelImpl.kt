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
}