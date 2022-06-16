package com.waewaee.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.views.pods.ActorListViewPod
import com.waewaee.themovieapp.views.pods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MovieDetailsActivity::class.java)
        }
    }

    //View Pods
    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorsViewPod: ActorListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setUpViewPods()
    }

    private fun setUpViewPods() {
        actorsViewPod = vpActorsInMovie as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            R.color.colorPrimary,
            getString(R.string.lbl_actors_in_movie),
            ""
        )

//        creatorsViewPod=vpCreatorsOfMovie as ActorListViewPod
//        creatorsViewPod.setUpActorViewPod(
//            R.color.colorPrimary,
//            getString(R.string.lbl_creators_of_movie),
//            getString(R.string.lbl_more_creators)
//
//        )
    }
}