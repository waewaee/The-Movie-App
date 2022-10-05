package com.waewaee.themovieapp.mvp.presenters

import com.waewaee.themovieapp.delegates.BannerViewHolderDelegate
import com.waewaee.themovieapp.delegates.MovieViewHolderDelegate
import com.waewaee.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.waewaee.themovieapp.mvp.views.MainView

interface MainPresenter: IBasePresenter, BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate {
    fun initView(view: MainView)
    fun onTapGenre(genrePosition: Int)
}