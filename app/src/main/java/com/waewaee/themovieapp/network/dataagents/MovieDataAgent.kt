package com.waewaee.themovieapp.network.dataagents

import com.waewaee.themovieapp.data.vos.MovieVO

interface MovieDataAgent {
    fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    )

    fun getPopularMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    )

    fun getTopRatedMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    )

}