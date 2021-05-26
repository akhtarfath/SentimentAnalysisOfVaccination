package com.aplikasikaryaanakbangkit.sentiment.sentiment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TweetEntity
import com.aplikasikaryaanakbangkit.sentiment.databinding.ItemTwitterPostBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SentimentAnalysisAdapter : RecyclerView.Adapter<SentimentAnalysisAdapter.TweetViewHolder>(){

    private var listTweet = ArrayList<TweetEntity>()

    fun setTweet(tweet: List<TweetEntity>) {
        this.listTweet.clear()
        this.listTweet.addAll(tweet)

        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val itemTweetBinding =
            ItemTwitterPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TweetViewHolder(itemTweetBinding)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val article = listTweet[position]
        holder.bind(article)
    }

    class TweetViewHolder(private val binding: ItemTwitterPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tweet: TweetEntity) {
            with(binding) {
                twitterPost.text = tweet.text

                dateTwitterPost.text = tweet.date
                nameUserTwitter.text = tweet.name
                usernameTwitter.text = tweet.username

                Glide.with(itemView.context)
                        .load(tweet.imageUrl)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error)
                        )
                        .into(twitterPhoto)
            }

        }
    }

    override fun getItemCount(): Int = listTweet.size
}