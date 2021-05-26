package com.aplikasikaryaanakbangkit.sentiment.news.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.ActivityDetailNewsBinding
import kotlin.properties.Delegates

class DetailVaccineNewsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SHOW = "extra_show"
    }

    private lateinit var _activityDetailNewsBinding: ActivityDetailNewsBinding
    private var _url by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityDetailNewsBinding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(_activityDetailNewsBinding.root)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val newsViewModel = ViewModelProvider(this, factory)[DetailNewsViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            true.loading()
            _url = extras.getString(EXTRA_SHOW).toString()
            newsViewModel.setSelectedDetailNews(_url)

            newsViewModel.getDataDetailVaccineNews.observe(this, { news ->
                if (news != null) {
                    when (news.status) {
                        Status.LOADING -> true.loading()
                        Status.SUCCESS -> if (news.data != null) {
                            false.loading()

                            // web view
                            val newsDetailWebView = _activityDetailNewsBinding.newsDetailWebView
                            newsDetailWebView.webViewClient = WebViewClient()
                            newsDetailWebView.loadUrl(news.data.url)
                        }
                        Status.ERROR -> {
                            false.loading()
                            Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })
        }
    }

    private fun Boolean.loading() {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        if (this) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
