package com.waewaee.themovieapp.network.dataagents

import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.network.responses.MovieListResponse
import com.waewaee.themovieapp.network.responses.TheMovieApi
import com.waewaee.themovieapp.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl: MovieDataAgent {

    private var mTheMovieApi: TheMovieApi? = null

    init {
        val mOkHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieApi = retrofit.create(TheMovieApi::class.java)
    }

    override fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>)  -> Unit,
        onFailure : (String) -> Unit
    ) {
        mTheMovieApi?.getNowPlayingMovies()
            ?.enqueue(object  : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.results ?: listOf()
                        onSuccess(movieList)
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }
}