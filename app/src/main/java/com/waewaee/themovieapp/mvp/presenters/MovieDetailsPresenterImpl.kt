package com.waewaee.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl
import com.waewaee.themovieapp.mvp.views.MovieDetailsView

class MovieDetailsPresenterImpl: ViewModel() ,MovieDetailsPresenter {

    // Model
    private val mMovieModel: MovieModel = MovieModelImpl

    // View
    private var mView: MovieDetailsView? = null

    override fun initView(view: MovieDetailsView) {
        mView = view
    }

    override fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int) {
        // Movie Details
        mMovieModel.getMovieDetails(movieId = movieId.toString()) {
            mView?.showError(it)
        }?.observe(owner) {
            it?.let {
                mView?.showMovieDetails(it)
            }
        }

        // Credits
        mMovieModel.getCreditsByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                mView?.showCreditsByMovie(cast = it.first, crew = it.second)
            },
            onFailure = {
                mView?.showError(it)
            })
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onReadyUI(owner: LifecycleOwner) {

    }
}