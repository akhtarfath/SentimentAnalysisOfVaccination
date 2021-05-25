package com.aplikasikaryaanakbangkit.sentiment.sentiment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TweetEntity
import com.aplikasikaryaanakbangkit.sentiment.databinding.ItemTwitterPostBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SentimentAnalysisAdapter :
    PagedListAdapter<TweetEntity, SentimentAnalysisAdapter.TweetViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TweetEntity>() {
            override fun areItemsTheSame(
                oldItem: TweetEntity,
                newItem: TweetEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TweetEntity,
                newItem: TweetEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val itemTweetBinding =
            ItemTwitterPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TweetViewHolder(itemTweetBinding)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    class TweetViewHolder(private val binding: ItemTwitterPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tweet: TweetEntity) {
            with(binding) {

                Glide.with(itemView.context)
                    .load(tweet.id)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(twitterPhoto)
            }
        }
    }
}