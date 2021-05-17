package com.aplikasikaryaanakbangkit.sentiment.ui.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.data.source.local.entity.ArticleCovidEntity
import com.aplikasikaryaanakbangkit.sentiment.databinding.MiniItemBinding
import com.aplikasikaryaanakbangkit.sentiment.ui.newsdetail.DetailNewsActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
            MiniItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(miniItemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    class NewsViewHolder(private val binding: MiniItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleCovidEntity) {
            with(binding) {
                tvItemTitle.text = article.title
                tvItemAuthor.text = article.author
                tvItemDescription.text = article.description
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