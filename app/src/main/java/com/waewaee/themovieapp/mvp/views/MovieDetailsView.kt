package com.waewaee.themovieapp.mvp.views

import com.waewaee.themovieapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.MovieVO

interface MovieDetailsView: BaseView {
    fun showMovieDetails(movie: MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
}