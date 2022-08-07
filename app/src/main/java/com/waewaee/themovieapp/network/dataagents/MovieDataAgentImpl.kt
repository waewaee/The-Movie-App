package com.waewaee.themovieapp.network.dataagents

import android.os.AsyncTask
import android.os.AsyncTask.execute
import android.util.Log
import com.google.gson.Gson
import com.waewaee.themovieapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.network.responses.MovieListResponse
import com.waewaee.themovieapp.utils.API_GET_NOW_PLAYING
import com.waewaee.themovieapp.utils.BASE_URL
import com.waewaee.themovieapp.utils.MOVIE_API_KEY
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

object MovieDataAgentImpl: MovieDataAgent {
//    override fun getNowPlayingMovies() {
//        GetNowPlayingMoviesTask().execute()
//    }

    class GetNowPlayingMoviesTask() : AsyncTask<Void, Void, MovieListResponse?>() {
        override fun doInBackground(vararg params: Void?): MovieListResponse? {
            val url: URL

            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                url = URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"

                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(
                    InputStreamReader(connection.inputStream)
                )

                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("Now Playing Movies", responseString)

                val movieListResponse = Gson().fromJson(
                    responseString,
                    MovieListResponse::class.java
                )

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("NewsError", e.message ?: "")
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (ioe: IOException) {
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }

        override fun onPostExecute(result: MovieListResponse?) {
            super.onPostExecute(result)
        }
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

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

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        //
    }
}