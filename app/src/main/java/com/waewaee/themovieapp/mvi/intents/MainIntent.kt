package com.waewaee.themovieapp.mvi.intents

import com.waewaee.themovieapp.mvi.mvibase.MVIIntent

sealed class MainIntent : MVIIntent {
    class LoadMoviesByGenreIntent(val genrePosition: Int) : MainIntent()
    object LoadAllHomePageData : MainIntent()
}