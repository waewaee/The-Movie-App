package com.waewaee.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.waewaee.themovieapp.R
import com.waewaee.themovieapp.adapters.BannerAdapter
import com.waewaee.themovieapp.dummy.dummyGenreList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mBannerAdapter: BannerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Appbar Leading Icon
        setUpToolbar()
        setUpBannerViewPager()
        setUpGenreTabLayout()
        setUpListeners()

    }

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

    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter()
        viewPagerBanner.adapter = mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }
}