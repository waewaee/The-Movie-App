package com.waewaee.themovieapp.mvi.mvibase

interface MVIView<S : MVIState> {
    fun render(state : S)
}