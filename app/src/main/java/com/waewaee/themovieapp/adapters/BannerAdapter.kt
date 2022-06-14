package com.waewaee.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.delegates.BannerViewHolderDelegate
import com.waewaee.themovieapp.views.holders.BannerViewHolder

class BannerAdapter(val mDelegate: BannerViewHolderDelegate): RecyclerView.Adapter<BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_banner, parent, false)
        return BannerViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}