package com.waewaee.themovieapp.data.models

import androidx.lifecycle.LiveData
import com.waewaee.themovieapp.data.vos.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl: BaseModel(), MovieModel {

//    // Data Agent
//    val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl
//
//    // Database
//    var mMovieDatabase: MovieDatabase? = null
//
//    fun initDatabase(context: Context) {
//        mMovieDatabase = MovieDatabase.getDbInstance(context)
//    }

    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) : LiveData<List<MovieVO>>? {
        // Database
//        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())

        // Network
//        mMovieDataAgent.getNowPlayingMovies(onSuccess = {
//
//            it.forEach { movie -> movie.type = NOW_PLAYING }
//            mMovieDatabase?.movieDao()?.insertMovies(it)
//
//            onSuccess(it)
//        }, onFailure = onFailure)

        mMovieApi!!.getNowPlayingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

//                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING)
    }

    override fun getPopularMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) : LiveData<List<MovieVO>>? {
//        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR) ?: listOf())

//        mMovieDataAgent.getPopularMovies(onSuccess = {
//
//             it.forEach { movie -> movie.type= POPULAR }
//            mMovieDatabase?.movieDao()?.insertMovies(it)
//
//            onSuccess(it)
//        }, onFailure = onFailure)

        mMovieApi!!.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type= POPULAR }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

//                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR)
    }

    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) : LiveData<List<MovieVO>>? {
//        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED) ?: listOf())

//        mMovieDataAgent.getTopRatedMovies(onSuccess = {
//
//              it.forEach { movie -> movie.type = TOP_RATED }
//            mMovieDatabase?.movieDao()?.insertMovies(it)
//
//            onSuccess(it)
//        }, onFailure = onFailure)

        mMovieApi!!.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type= TOP_RATED }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

//                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED)
    }

    override fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.getGenres(onSuccess = onSuccess, onFailure = onFailure)

        mMovieApi!!.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.genres ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.getMoviesByGenre(
//            genreId = genreId,
//            onSuccess = onSuccess,
//            onFailure = onFailure
//        )

        mMovieApi!!.getMoviesByGenre(genreId = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })

    }

    override fun getActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.getActors(onSuccess = onSuccess, onFailure = onFailure)
        mMovieApi!!.getActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getMovieDetails(
        movieId: String,
//        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) : LiveData<MovieVO?>? {
        // Database
//        val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
//        movieFromDatabase?.let {
//            onSuccess(it)
//        }

        // Network
//        mMovieDataAgent.getMovieDetails(
//            movieId = movieId,
//            onSuccess = {
//
//                val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
//                it.type = movieFromDatabase?.type
//
//                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
//
//                onSuccess(it)
//            },
//            onFailure = onFailure
//        )

        mMovieApi!!.getMovieDetails(movieId =  movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDatabaseToSync = mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabaseToSync?.type

                mMovieDatabase?.movieDao()?.insertSingleMovie(it)

//                onSuccess(it)
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.getCreditsByMovie(
//            movieId = movieId,
//            onSuccess = onSuccess,
//            onFailure = onFailure
//        )

        mMovieApi!!.getCreditsByMovie(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess( Pair(it.cast ?: listOf(), it.crew ?: listOf()) )
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun searchMovie(query: String): Observable<List<MovieVO>> {
        return mMovieApi!!
            .searchMovie(query = query)
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }

    override fun getNowPlayingMoviesObservable(): Observable<List<MovieVO>>? {
        mMovieApi!!.getNowPlayingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            }

        return mMovieDatabase?.movieDao()
            ?.getMoviesByTypeFlowable(type = NOW_PLAYING)
            ?.toObservable()
    }

    override fun getPopularMoviesObservable(): Observable<List<MovieVO>>? {
        mMovieApi!!.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.results?.forEach { movie -> movie.type= POPULAR }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            }

        return mMovieDatabase?.movieDao()
            ?.getMoviesByTypeFlowable(type = POPULAR)
            ?.toObservable()
    }

    override fun getTopRatedMoviesObservable(): Observable<List<MovieVO>>? {
        mMovieApi!!.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.results?.forEach { movie -> movie.type= TOP_RATED }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            }

        return mMovieDatabase?.movieDao()
            ?.getMoviesByTypeFlowable(type = TOP_RATED)
            ?.toObservable()
    }

    override fun getGenresObservable(): Observable<List<GenreVO>>? {
        return mMovieApi!!.getGenres()
            .map { it.genres ?: listOf() }
            .subscribeOn(Schedulers.io())
    }

    override fun getActorsObservable(): Observable<List<ActorVO>>? {
        return mMovieApi!!.getActors()
            .map { it.results ?: listOf() }
            .subscribeOn(Schedulers.io())
    }

    override fun getMoviesByGenreObservable(genreId: String): Observable<List<MovieVO>>? {
        return mMovieApi!!.getMoviesByGenre(genreId = genreId)
            .map { it.results ?: listOf() }
            .subscribeOn(Schedulers.io())
    }

    override fun getMovieByIdObservable(movieId: Int): Observable<MovieVO?>? {
        return null
    }

    override fun getCreditsByMovieObservable(movieId: Int): Observable<Pair<List<ActorVO>, List<ActorVO>>>? {
        return null
    }
}