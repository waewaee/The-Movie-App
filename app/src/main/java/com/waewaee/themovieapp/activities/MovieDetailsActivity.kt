package com.waewaee.themovieapp.activities

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.utils.IMAGE_BASE_URL
import com.waewaee.themovieapp.views.pods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private val mMovieModel: MovieModel = MovieModelImpl

    companion object {

        private val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    //View Pods
    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorsViewPod: ActorListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setUpViewPods()
        setUpListeners()

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
//        Snackbar.make(window.decorView, "$movieId", Snackbar.LENGTH_SHORT).show()
        movieId?.let {
            requestData(it)
        }
    }

    private fun requestData(movieId: Int) {
        mMovieModel.getMovieDetails(
            movieId = movieId.toString(),
//            onSuccess = {
//                bindData(it)
//            },
            onFailure = {
                // Show error msg
            }
        )?.observe(this) {
            it?.let {
                bindData(it)
            }
        }

        mMovieModel.getCreditsByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                actorsViewPod.setData(it.first)
                creatorsViewPod.setData(it.second)
            },
            onFailure = {

            }
        )
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