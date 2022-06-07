package com.waewaee.themovieapp.views.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.waewaee.themovieapp.adapters.MovieAdapter
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mMovieAdapter: MovieAdapter

    override fun onFinishInflate() {
        setUpMovieRecyclerView()
        super.onFinishInflate()
    }

    private fun setUpMovieRecyclerView() {
        mMovieAdapter = MovieAdapter()
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}