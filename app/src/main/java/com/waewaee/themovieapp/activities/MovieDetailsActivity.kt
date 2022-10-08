package com.waewaee.themovieapp.activities

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl
import com.waewaee.themovieapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.mvp.presenters.MovieDetailsPresenter
import com.waewaee.themovieapp.mvp.presenters.MovieDetailsPresenterImpl
import com.waewaee.themovieapp.mvp.views.MovieDetailsView
import com.waewaee.themovieapp.mvvm.MovieDetailsViewModel
import com.waewaee.themovieapp.utils.IMAGE_BASE_URL
import com.waewaee.themovieapp.views.pods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object {

        private val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    // View Pods
    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorsViewPod: ActorListViewPod

    // View Model
    lateinit var mViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let {
            setUpViewModel(it)
        }

        setUpViewPods()
        setUpListeners()
        observeLiveData()

    }

    private fun observeLiveData() {
        mViewModel.movieDetailsLiveData?.observe(this) {
            it?.let { movie -> bindData(movie) }
        }
        mViewModel.castLiveData.observe(this, actorsViewPod::setData)
        mViewModel.crewLiveData.observe(this, creatorsViewPod::setData)
    }

    private fun setUpViewModel(movieId: Int) {
        mViewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        mViewModel.getInitialData(movieId)
    }

    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieImage)

        tvMovieName.text = movie.title ?: ""
        tvReleaseYear.text = movie.releaseDate?.substring(0, 4)
        tvRating.text = movie.voteAverage?.toString() ?: ""
        movie.voteCount?. let {
            tvNoOfVotes.text = "$it VOTES"
        }
        rbMovieRating.rating = movie.getRatingBasedOnFiveStars()

        bindGenres(movie, movie.genres ?: listOf())

        tvOverview.text = movie.overView ?: ""
        tvOriginalTitle.text = movie.originalTitle ?: ""
        tvMovieType.text = movie.getGenresAsCommaSeparatedString()
        tvProduction.text = movie.getProductionCountriesAsCommaSeparatedString()
        tvPremiere.text = movie.releaseDate ?: ""
        tvDescription.text = movie.overView ?: ""
    }

    private fun bindGenres(
        movie: MovieVO,
        genres: List<GenreVO>
    ) {
        movie.genres?.count()?.let {
            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""

            if (it < 3) {
                tvThirdGenre.visibility = View.GONE
            } else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }
        }
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPods() {
        actorsViewPod = vpActorsInMovie as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors_in_movie),
            moreTitleText = ""
        )

        creatorsViewPod=vpCreatorsOfMovie as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators_of_movie),
            moreTitleText = getString(R.string.lbl_more_creators)

        )
    }
}