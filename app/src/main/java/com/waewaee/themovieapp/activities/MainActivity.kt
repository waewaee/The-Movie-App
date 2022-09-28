package com.waewaee.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.adapters.BannerAdapter
import com.waewaee.themovieapp.adapters.ShowcaseAdapter
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.delegates.BannerViewHolderDelegate
import com.waewaee.themovieapp.delegates.MovieViewHolderDelegate
import com.waewaee.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.waewaee.themovieapp.dummy.dummyGenreList
import com.waewaee.themovieapp.network.dataagents.MovieDataAgentImpl
import com.waewaee.themovieapp.network.dataagents.OkHTTPDataAgentImpl
import com.waewaee.themovieapp.views.pods.ActorListViewPod
import com.waewaee.themovieapp.views.pods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate {

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMoviesByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

    // Model
    private val mMovieModel: MovieModel = MovieModelImpl

    // Data
    private var mGenres: List<GenreVO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()
        setUpViewPods()
        setUpBannerViewPager()
//        setUpGenreTabLayout()
        setUpShowcaseRecyclerView()
        setUpListeners()

        // Request Data
        requestData()

    }

    private fun requestData() {

        // Now Playing Movies
//        MovieDataAgentImpl.getNowPlayingMovies()
//        OkHTTPDataAgentImpl.getNowPlayingMovies()

        mMovieModel.getNowPlayingMovies(
//            onSuccess = {
//                mBannerAdapter.setNewData(it)
//            },
            onFailure = {
                // Show Error Msg
            }
        )?.observe(this) {
            mBannerAdapter.setNewData(it)
        }

        // Popular Movies
        mMovieModel.getPopularMovies(
//            onSuccess = {
//                mBestPopularMovieListViewPod.setData(it)
//                mMoviesByGenreViewPod.setData(it) <-- this one is for testing
//            },
            onFailure = {
                // Show error msg
            }
        )?.observe(this) {
            mBestPopularMovieListViewPod.setData(it)
        }

        // Top Rated Movies
        mMovieModel.getTopRatedMovies(
//            onSuccess = {
//                mShowcaseAdapter.setNewData(it)
//            },
            onFailure = {
                // Show error msg
            }
        )?.observe(this) {
            mShowcaseAdapter.setNewData(it)
        }

        // Get Genres
        mMovieModel.getGenres(
            onSuccess = {
                mGenres = it
                setUpGenreTabLayout(it)

                // Get Movies By Genre for First Genre
                it.firstOrNull()?.id?.let { genreId ->
                    getMoviesByGenre(genreId)
                }
            },
            onFailure = {
                // Show error msg
            }
        )

        // Get Actors
        mMovieModel.getActors(
            onSuccess = {
                mActorListViewPod.setData(it)
            },
            onFailure = {
                // Show error msg
            }
        )

    }

    private fun getMoviesByGenre(genreId: Int) {
        mMovieModel.getMoviesByGenre(
            genreId = genreId.toString(),
            onSuccess = {
                mMoviesByGenreViewPod.setData(it)
            },
            onFailure = {
                // Show error msg
            }
        )
    }

    // --- View Pods ---
    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)

        mMoviesByGenreViewPod = vpMoviesByGenre as MovieListViewPod
        mMoviesByGenreViewPod.setUpMovieListViewPod(this)

        mActorListViewPod = vpActorList as ActorListViewPod
    }

    // --- Listeners ---
    private fun setUpListeners() {

        // Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Snackbar.make(window.decorView, tab?.text ?: "", Snackbar.LENGTH_SHORT).show()

                mGenres?.get(tab?.position ?: 0)?.id?.let {
                    getMoviesByGenre(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    // --- Banner ---
    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(this)
        viewPagerBanner.adapter = mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    // --- Toolbar ---
    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        //Appbar Leading Icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    // --- Tab Layout ---
    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
//        dummyGenreList.forEach {
////            val tab = tabLayoutGenre.newTab()
////            tab.text = it
////            tabLayoutGenre.addTab(tab)
//
//            //Scope Function
//            tabLayoutGenre.newTab().apply {
//                text = it
//                tabLayoutGenre.addTab(this)
//            }
//        }

        genreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }
    }

    // --- Showcase ---
    private fun setUpShowcaseRecyclerView() {
        mShowcaseAdapter = ShowcaseAdapter(this)
        rvShowCase.adapter = mShowcaseAdapter
        rvShowCase.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    // --- Menu ---
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onTapMovieFromBanner(movieId: Int) {
//        Snackbar.make(window.decorView, "Tapped Movie from Banner & Movie Id $movieId", Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
//        Snackbar.make(window.decorView, "Tapped Movie from Showcase & Movie Id $movieId", Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }

    override fun onTapMovie(movieId: Int) {
//        Snackbar.make(window.decorView, "Tapped Movie from Best Popular Movies or Genres & Movie Id $movieId", Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }

}