package com.aplikasikaryaanakbangkit.sentiment.news.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
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

class DetailVaccineNewsActivity : AppCompatActivity(), MenuItem.OnMenuItemClickListener {
    companion object {
        const val EXTRA_SHOW = "extra_show"
    }

    private lateinit var _activityDetailNewsBinding: ActivityDetailNewsBinding
    private var _url by Delegates.notNull<String>()

    private var newsTitle = ""
    private var newsUrl = ""

    private var webView: WebView? = null

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
                            newsTitle = news.data.title.toString()
                            newsUrl = news.data.url

                            // web view
                            webView = findViewById(R.id.newsDetailWebView)
                            webView?.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
                            webView?.webViewClient = WebViewClient()
                            webView?.loadUrl(newsUrl)
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
            R.id.refresh -> {
                webView?.reload()
            }
            R.id.openInBrowser -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl)))
            }
        }
        return true
    }
}
