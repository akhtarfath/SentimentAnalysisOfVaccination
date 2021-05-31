package com.aplikasikaryaanakbangkit.sentiment.news.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.ActivityDetailNewsBinding
import kotlin.properties.Delegates


class DetailNewsActivity : AppCompatActivity(), MenuItem.OnMenuItemClickListener {

    companion object {
        const val EXTRA_SHOW = "extra_show"
    }

    private lateinit var _activityDetailNewsBinding: ActivityDetailNewsBinding
    private var _url by Delegates.notNull<String>()

    private var newsTitle = ""
    private var newsUrl = ""

    private var webView: WebView? = null
    private var progressBar: LottieAnimationView? = null

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
            _url = extras.getString(EXTRA_SHOW).toString()
            newsViewModel.setSelectedDetailNews(_url)

            newsViewModel.getDataDetailCovidHeadlines.observe(this, { news ->
                if (news != null) {
                    when (news.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> if (news.data != null) {
                            newsTitle = news.data.title.toString()
                            newsUrl = news.data.url

                            // web view
                            webView = findViewById(R.id.newsDetailWebView)
                            progressBar = findViewById(R.id.progressBar)

                            webView?.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
                            webView?.webViewClient = WebViewClient()
                            webView?.loadUrl(newsUrl)
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_bar_option_menu, menu)

        val shareView = menu.findItem(R.id.share)
        shareView.setOnMenuItemClickListener(this)

        val openInBrowser = menu.findItem(R.id.openInBrowser)
        openInBrowser.setOnMenuItemClickListener(this)

        return true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                            Intent.EXTRA_TEXT,
                            """
                            "$newsTitle".
                            
                            Kunjungi: $newsUrl
                        """.trimIndent()
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            R.id.openInBrowser -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl)))
            }
        }
        return true
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar?.visibility = View.GONE
        }
    }
}
