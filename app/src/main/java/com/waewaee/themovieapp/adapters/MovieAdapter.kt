package com.waewaee.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.delegates.MovieViewHolderDelegate
import com.waewaee.themovieapp.views.holders.MovieViewHolder

class MovieAdapter(val delegate: MovieViewHolderDelegate): RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_movie, parent, false)
        return MovieViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}