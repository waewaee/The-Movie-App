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
import com.waewaee.themovieapp.delegates.BannerViewHolderDelegate
import com.waewaee.themovieapp.delegates.MovieViewHolderDelegate
import com.waewaee.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.waewaee.themovieapp.dummy.dummyGenreList
import com.waewaee.themovieapp.network.dataagents.MovieDataAgentImpl
import com.waewaee.themovieapp.network.dataagents.OkHTTPDataAgentImpl
import com.waewaee.themovieapp.views.pods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate {

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMoviesByGenreViewPod: MovieListViewPod

    // Model
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()
        setUpViewPods()
        setUpBannerViewPager()
        setUpGenreTabLayout()
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
            onSuccess = {
                mBannerAdapter.setNewData(it)
            },
            onFailure = {
                // Show Error Msg
            }
        )

        // Popular Movies
        mMovieModel.getPopularMovies(
            onSuccess = {
                mBestPopularMovieListViewPod.setData(it)
            },
            onFailure = {
                // Show error msg
            }
        )

    // Top Rated Movies
        mMovieModel.getTopRatedMovies(
            onSuccess = {
                mShowcaseAdapter.setNewData(it)
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
    }

    // --- Listeners ---
    private fun setUpListeners() {

        // Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Snackbar.make(window.decorView, tab?.text ?: "", Snackbar.LENGTH_SHORT).show()
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
    private fun setUpGenreTabLayout() {
        dummyGenreList.forEach {
//            val tab = tabLayoutGenre.newTab()
//            tab.text = it
//            tabLayoutGenre.addTab(tab)

            //Scope Function
            tabLayoutGenre.newTab().apply {
                text = it
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

    override fun onTapMovieFromBanner() {
//        Snackbar.make(window.decorView, "Tapped Movie from Banner", Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this))
    }

    override fun onTapMovieFromShowcase() {
//        Snackbar.make(window.decorView, "Tapped Movie from Showcase", Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this))
    }

    override fun onTapMovie() {
//        Snackbar.make(window.decorView, "Tapped Movie from Best Popular Movies or Genres", Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this))
    }

}