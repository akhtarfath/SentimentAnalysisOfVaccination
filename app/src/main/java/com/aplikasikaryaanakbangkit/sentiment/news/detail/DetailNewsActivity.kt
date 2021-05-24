package com.aplikasikaryaanakbangkit.sentiment.news.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.ActivityDetailNewsBinding
import com.aplikasikaryaanakbangkit.sentiment.databinding.ContentDetailNewsBinding
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

        supportActionBar?.title = ""
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
        }
    }

    private fun loading(state: Boolean) {
        if (state) _activityDetailNewsBinding.progressBar.visibility = View.VISIBLE
        else _activityDetailNewsBinding.progressBar.visibility = View.GONE
    }

}
