package com.waewaee.themovieapp.views.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.waewaee.themovieapp.adapters.MovieAdapter
import com.waewaee.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mMovieAdapter: MovieAdapter
    lateinit var mDelegate: MovieViewHolderDelegate

    override fun onFinishInflate() {
//        setUpMovieRecyclerView()
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate) {
        setDelegate(delegate)
        setUpMovieRecyclerView()
    }

    private fun setDelegate(delegate: MovieViewHolderDelegate) {
        mDelegate = delegate
    }

    private fun setUpMovieRecyclerView() {
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}