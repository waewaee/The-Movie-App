package com.waewaee.themovieapp.views.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.themovieapp.delegates.ShowcaseViewHolderDelegate

class ShowcaseViewHolder(itemView: View, private val mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            mDelegate.onTapMovieFromShowcase()
        }
    }
}