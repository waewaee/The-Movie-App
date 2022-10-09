package com.waewaee.themovieapp.routers

import android.app.Activity
import com.waewaee.themovieapp.activities.MovieDetailsActivity
import com.waewaee.themovieapp.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailsActivity(movieId: Int) {
    startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
}

fun Activity.navigateToMovieSearchActivity() {
    startActivity(MovieSearchActivity.newIntent(this))
}