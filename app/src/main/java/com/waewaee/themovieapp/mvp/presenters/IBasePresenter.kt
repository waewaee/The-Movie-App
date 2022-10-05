package com.waewaee.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {
    fun onReadyUI(owner: LifecycleOwner)
}