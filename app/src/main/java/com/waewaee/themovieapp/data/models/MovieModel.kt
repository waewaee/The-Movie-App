package com.waewaee.themovieapp.data.models

import com.waewaee.themovieapp.data.vos.MovieVO

interface MovieModel {
    fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>)  -> Unit,
        onFailure : (String) -> Unit
    )
}