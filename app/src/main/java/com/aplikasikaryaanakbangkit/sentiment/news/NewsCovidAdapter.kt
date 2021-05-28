package com.aplikasikaryaanakbangkit.sentiment.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.news.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.databinding.MiniItemHorizontalBinding
import com.aplikasikaryaanakbangkit.sentiment.news.detail.DetailNewsActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class NewsCovidAdapter :
    PagedListAdapter<ArticleCovidEntity, NewsCovidAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticleCovidEntity>() {
            override fun areItemsTheSame(
                    oldItem: ArticleCovidEntity,
                    newItem: ArticleCovidEntity
            ): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(
                    oldItem: ArticleCovidEntity,
                    newItem: ArticleCovidEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val miniItemBinding =
            MiniItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(miniItemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    class NewsViewHolder(private val binding: MiniItemHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleCovidEntity) {
            with(binding) {

                val publishedAt = LocalDateTime
                    // Represent a date with time-of-day but lacking offset-from-UTC or time zone.
                    // As such, this does *not* represent a moment, is *not* a point on the timeline.
                    .parse(
                        article.publishedAt,
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

                tvItemTitle.text = article.title
                tvPublishedAt.text = publishedAt

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailNewsActivity::class.java)
                    intent.putExtra(DetailNewsActivity.EXTRA_SHOW, article.url)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(article.urlToImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imageNews)
            }
        }
    }
}