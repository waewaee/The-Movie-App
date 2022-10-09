package com.waewaee.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.interactors.MovieIneractorImpl
import com.waewaee.themovieapp.interactors.MovieInteractor
import com.waewaee.themovieapp.mvp.views.MainView

class MainPresenterImpl: ViewModel(), MainPresenter {

    // View
    var mView: MainView? = null

    // Interactor
    private val mMovieInteractor: MovieInteractor = MovieIneractorImpl

    // States
    private var mGenres: List<GenreVO>? = listOf()

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onReadyUI(owner: LifecycleOwner) {
        // Now Playing
        mMovieInteractor.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showNowPlayingMovies(it)
        }

        // Popular
        mMovieInteractor.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showPopularMovies(it)
        }

        // Top Rated
        mMovieInteractor.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showTopRatedMovies(it)
        }

        // Genre and Get Movies for First Genre
        mMovieInteractor.getGenres(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let { firstGenreId ->
                    onTapGenre(firstGenreId)
                }
            },
            onFailure = {
                mView?.showError(it)
            }
        )

        // Actors
        mMovieInteractor.getActors(
            onSuccess = {
                mView?.showActors(it)
            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapGenre(genrePosition: Int) {
        mGenres?.getOrNull(genrePosition)?.id?.let { genreId ->
            mMovieInteractor.getMoviesByGenre(
                genreId = genreId.toString(),
                onSuccess = {
                    mView?.showMoviesByGenre(it)
                },
                onFailure = {
                    mView?.showError(it)
                })
        }
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }
}