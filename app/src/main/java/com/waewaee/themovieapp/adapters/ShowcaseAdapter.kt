package com.waewaee.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.waewaee.themovieapp.views.holders.ShowcaseViewHolder

class ShowcaseAdapter(val mDelegate: ShowcaseViewHolderDelegate): RecyclerView.Adapter<ShowcaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_showcase, parent, false)
        return ShowcaseViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }
}