package com.waewaee.themovieapp.views.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.themovieapp.delegates.BannerViewHolderDelegate

class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            mDelegate.onTapMovieFromBanner()
        }
    }
}