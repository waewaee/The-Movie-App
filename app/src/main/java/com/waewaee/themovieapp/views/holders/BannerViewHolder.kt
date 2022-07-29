package com.waewaee.themovieapp.views.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.delegates.BannerViewHolderDelegate
import com.waewaee.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_item_banner.view.*

class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            mDelegate.onTapMovieFromBanner()
        }
    }

    fun bindData(movie: MovieVO) {
        Glide.with(itemView.context)
            .load("${ IMAGE_BASE_URL}${movie.posterPath}")
            .placeholder(R.drawable.the_wolverine_image)
            .into(itemView.ivBannerImage)

        itemView.tvBannerMovieName.text = movie.title
    }
}