package com.waewaee.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.views.holders.ActorViewHolder

class ActorAdapter: RecyclerView.Adapter<ActorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}