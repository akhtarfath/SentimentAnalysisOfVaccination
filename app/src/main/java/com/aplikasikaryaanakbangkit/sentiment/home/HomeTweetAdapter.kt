package com.aplikasikaryaanakbangkit.sentiment.home

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.tweet.TweetEntity
import com.aplikasikaryaanakbangkit.sentiment.databinding.MiniItemTwitterPostBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList

class HomeTweetAdapter : RecyclerView.Adapter<HomeTweetAdapter.TweetViewHolder>() {

    private var listTweet = ArrayList<TweetEntity>()

    fun setTweet(tweet: List<TweetEntity>) {
        this.listTweet.clear()
        this.listTweet.addAll(tweet)

        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val miniItemTweetBinding =
                MiniItemTwitterPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TweetViewHolder(miniItemTweetBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val article = listTweet[position]
        holder.bind(article)
    }

    class TweetViewHolder(private val binding: MiniItemTwitterPostBinding) :
            RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(tweet: TweetEntity) {
            with(binding) {
                var sentimentAnalysis = ""

                val date = LocalDateTime.parse(
                        tweet.date,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000'Z'")
                ).toLocalDate()
                        .format(
                                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                                        .withLocale(Locale("in", "ID", "ID"))
                        )

                when (tweet.analysis) {
                    "Positive" -> {
                        sentimentAnalysis = "Pro"
                        tvSentimentResult.setTextColor(
                                ContextCompat.getColor(itemView.context, R.color.google_green)
                        )
                    }
                    "Negative" -> {
                        sentimentAnalysis = "Kontra"
                        tvSentimentResult.setTextColor(
                                ContextCompat.getColor(itemView.context, R.color.google_red)
                        )
                    }
                    "Neutral" -> {
                        sentimentAnalysis = "Netral"
                        tvSentimentResult.setTextColor(
                                ContextCompat.getColor(itemView.context, R.color.google_yellow)
                        )
                    }
                }

                tvTwitterPost.text = tweet.text
                tvDateTwitterPost.text = date
                tvNameUserTwitter.text = tweet.name
                tvUsernameTwitter.text = StringBuilder("@${tweet.username}")
                tvSentimentResult.text = sentimentAnalysis

                replyCount.text = tweet.replyCount.toString()
                retweetCount.text = tweet.retweetCount.toString()
                likeCount.text = tweet.likeCount.toString()

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

    override fun getItemCount(): Int = 3
}