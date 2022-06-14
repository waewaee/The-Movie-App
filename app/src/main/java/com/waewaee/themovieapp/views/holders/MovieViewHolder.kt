package com.waewaee.themovieapp.views.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.themovieapp.delegates.MovieViewHolderDelegate

class MovieViewHolder(itemView: View, private val delegate: MovieViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            delegate.onTapMovie()
        }
    }
}