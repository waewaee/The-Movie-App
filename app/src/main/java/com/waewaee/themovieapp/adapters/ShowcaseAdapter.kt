package com.waewaee.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.waewaee.themovieapp.views.holders.ShowcaseViewHolder

class ShowcaseAdapter(val mDelegate: ShowcaseViewHolderDelegate): RecyclerView.Adapter<ShowcaseViewHolder>() {

    private var mMovieList: List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_showcase, parent, false)
        return ShowcaseViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }
}