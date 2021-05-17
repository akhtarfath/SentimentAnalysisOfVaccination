package com.aplikasikaryaanakbangkit.sentiment.ui.newsdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.databinding.ActivityDetailNewsBinding
import com.aplikasikaryaanakbangkit.sentiment.databinding.ContentDetailNewsBinding
import com.aplikasikaryaanakbangkit.sentiment.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.properties.Delegates

class DetailNewsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SHOW = "extra_show"
    }

    private lateinit var _detailContentBinding: ContentDetailNewsBinding
    private lateinit var _activityDetailNewsBinding: ActivityDetailNewsBinding
    private lateinit var _viewModel: DetailNewsViewModel
    private var _url by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityDetailNewsBinding = ActivityDetailNewsBinding.inflate(layoutInflater)
        _detailContentBinding = _activityDetailNewsBinding.detailContent
        setContentView(_activityDetailNewsBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        _viewModel = ViewModelProvider(this, factory)[DetailNewsViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            loading(true)
            _url = extras.getString(EXTRA_SHOW).toString()
            _viewModel.setSelectedDetailNews(_url)

            _viewModel.getDataDetailCovidHeadlines.observe(this, { news ->
                if (news != null) {
                    when (news.status) {
                        Status.LOADING -> loading(true)
                        Status.SUCCESS -> if (news.data != null) {
                            loading(false)
                            setNewsCovid(news.data)
                        }
                        Status.ERROR -> {
                            loading(false)
                            Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })

            _viewModel.getDataDetailVaccineNews.observe(this, { news ->
                if (news != null) {
                    when (news.status) {
                        Status.LOADING -> loading(true)
                        Status.SUCCESS -> if (news.data != null) {
                            loading(false)
                            setNewsVaccine(news.data)
                        }
                        Status.ERROR -> {
                            loading(false)
                            Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })
        }
    }

    private fun setNewsVaccine(data: ArticleVaccinesEntity) {
        with(_detailContentBinding) {
            title.text = data.title
            author.text = data.author
            publishedAt.text = data.publishedAt
            content.text = data.content

            Glide.with(this@DetailNewsActivity)
                .load(data.urlToImage)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imageNews)

            buttonSource.setOnClickListener {
                var url = data.url
                if (!url.startsWith("http://")) {
                    url = "http://$url"
                }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
            }
        }
    }

    private fun setNewsCovid(covidNews: ArticleCovidEntity) {
        with(_detailContentBinding) {
            title.text = covidNews.title
            author.text = covidNews.author
            publishedAt.text = covidNews.publishedAt
            content.text = covidNews.content

            Glide.with(this@DetailNewsActivity)
                .load(covidNews.urlToImage)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imageNews)

            buttonSource.setOnClickListener {
                var url = covidNews.url
                if (!url.startsWith("http://")) {
                    url = "http://$url"
                }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
            }
        }
    }

    private fun loading(state: Boolean) {
        if (state) _activityDetailNewsBinding.loading.progressBar.visibility = View.VISIBLE
        else _activityDetailNewsBinding.loading.progressBar.visibility = View.GONE
    }

}