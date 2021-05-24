package com.aplikasikaryaanakbangkit.sentiment.news.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleVaccinesEntity
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.ActivityDetailNewsBinding
import com.aplikasikaryaanakbangkit.sentiment.databinding.ContentDetailNewsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.properties.Delegates

class DetailVaccineNewsActivity : AppCompatActivity() {
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

            val datePublishedAt = LocalDateTime
                // Represent a date with time-of-day but lacking offset-from-UTC or time zone.
                // As such, this does *not* represent a moment, is *not* a point on the timeline.
                .parse(
                    data.publishedAt,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                )
                // Parse an input string in standard ISO 8601 format. Returns a `LocalDateTime` object.
                .toLocalDate()
                // Extract the date-only portion without the time-of-day.
                // Still no time zone or offset-from-UTC. Returns a `LocalDate` object.
                .format(
                    // Generate text representing the value of that `LocalDate` object.
                    DateTimeFormatter
                        // Define a pattern to use in generating text.
                        .ofLocalizedDate(FormatStyle.FULL)
                        // Automatically localize, specifying how long/abbreviated…
                        .withLocale(
                            Locale(
                                "in",
                                "ID",
                                "ID"
                            )
                        ) // … and what human language and cultural norms to use in localizing.
                )

            title.text = data.title
            author.text = data.author
            publishedAt.text = datePublishedAt
            content.text = data.content

            Glide.with(this@DetailVaccineNewsActivity)
                .load(data.urlToImage)
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

    override fun onNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
