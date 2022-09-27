package com.waewaee.themovieapp

import android.app.Application
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}