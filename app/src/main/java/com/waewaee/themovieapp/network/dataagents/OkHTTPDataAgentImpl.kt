package com.waewaee.themovieapp.network.dataagents

import android.os.AsyncTask
import com.google.gson.Gson
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.network.responses.MovieListResponse
import com.waewaee.themovieapp.utils.API_GET_NOW_PLAYING
import com.waewaee.themovieapp.utils.BASE_URL
import com.waewaee.themovieapp.utils.MOVIE_API_KEY
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object OkHTTPDataAgentImpl: MovieDataAgent {

    private val mClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetNowPlayingMovieOkHTTPTask(mClient).execute()
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    class GetNowPlayingMovieOkHTTPTask(
        private val mOkHttpClient: OkHttpClient,
    ) : AsyncTask<Void, Void, MovieListResponse>() {

        override fun doInBackground(vararg params: Void?): MovieListResponse? {

            val request = Request.Builder()
                .url("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
                .build()

            try {

                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        val response = Gson().fromJson<MovieListResponse>(
                            responseString,
                            MovieListResponse::class.java
                        )
                        return response
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null

        }

        override fun onPostExecute(result: MovieListResponse?) {
            super.onPostExecute(result)
        }
    }
}