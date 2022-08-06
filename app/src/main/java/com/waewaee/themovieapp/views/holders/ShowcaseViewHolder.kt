package com.waewaee.themovieapp.views.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.waewaee.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_item_showcase.view.*

class ShowcaseViewHolder(itemView: View, private val mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            mDelegate.onTapMovieFromShowcase()
        }
    }

    fun bindData(movie: MovieVO) {
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivShowCase)
        itemView.tvShowCaseMovieName.text = movie.title
        itemView.tvShowCaseMovieDate.text = movie.releaseDate
    }
}