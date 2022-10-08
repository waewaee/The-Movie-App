package com.waewaee.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.adapters.BannerAdapter
import com.waewaee.themovieapp.adapters.ShowcaseAdapter
import com.waewaee.themovieapp.data.models.MovieModel
import com.waewaee.themovieapp.data.models.MovieModelImpl
import com.waewaee.themovieapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.GenreVO
import com.waewaee.themovieapp.data.vos.MovieVO
import com.waewaee.themovieapp.delegates.BannerViewHolderDelegate
import com.waewaee.themovieapp.delegates.MovieViewHolderDelegate
import com.waewaee.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.waewaee.themovieapp.dummy.dummyGenreList
import com.waewaee.themovieapp.mvp.presenters.MainPresenter
import com.waewaee.themovieapp.mvp.presenters.MainPresenterImpl
import com.waewaee.themovieapp.mvp.views.MainView
import com.waewaee.themovieapp.mvvm.MainViewModel
import com.waewaee.themovieapp.network.dataagents.MovieDataAgentImpl
import com.waewaee.themovieapp.network.dataagents.OkHTTPDataAgentImpl
import com.waewaee.themovieapp.views.pods.ActorListViewPod
import com.waewaee.themovieapp.views.pods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate {

    // View Pods
    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMoviesByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

    // ViewModel
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()

        setUpToolbar()
        setUpViewPods()
        setUpBannerViewPager()
        setUpShowcaseRecyclerView()
        setUpListeners()

        // Observe Live Data
        observeLiveData()
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mViewModel.getInitialData()
    }

    private fun observeLiveData() {
        mViewModel.nowPlayingMoviesLiveData?.observe(this, mBannerAdapter::setNewData)
        mViewModel.popularMoviesLiveData?.observe(this, mBestPopularMovieListViewPod::setData)
        mViewModel.topRatedMoviesLiveData?.observe(this, mShowcaseAdapter::setNewData)
        mViewModel.genresLiveData.observe(this, this::setUpGenreTabLayout)
        mViewModel.moviesByGenreLiveData.observe(this, mMoviesByGenreViewPod::setData)
        mViewModel.actorsLiveData.observe(this, mActorListViewPod::setData)
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
                mViewModel.getMovieByGenre(tab?.position ?: 0)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionSearch -> startActivity(MovieSearchActivity.newIntent(this))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

}