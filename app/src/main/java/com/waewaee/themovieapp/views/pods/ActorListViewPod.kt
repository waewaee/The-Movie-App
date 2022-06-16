package com.waewaee.themovieapp.views.pods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.waewaee.themovieapp.adapters.ActorAdapter
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mBestActorAdapter: ActorAdapter

    override fun onFinishInflate() {
        setUpActorRecyclerView()
        super.onFinishInflate()
    }

    fun setUpActorViewPod(backgroundColorReference: Int, titleText: String, moreTitleText: String) {
        setBackgroundColor(ContextCompat.getColor(context, backgroundColorReference)) // called from view object// ActorListViewPod is already a view object cos it extends RelativeLayout
        tvBestActors.text = titleText
        tvMoreActors.text = moreTitleText
        tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun setUpActorRecyclerView() {
        mBestActorAdapter = ActorAdapter()
        rvBestActors.adapter = mBestActorAdapter
        rvBestActors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}