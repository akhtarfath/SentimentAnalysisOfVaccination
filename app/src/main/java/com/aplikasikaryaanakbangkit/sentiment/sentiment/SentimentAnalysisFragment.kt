package com.aplikasikaryaanakbangkit.sentiment.sentiment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment.TextTweet
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentSentimentAnalysisBinding
import kotlinx.android.synthetic.main.fragment_home.*

class SentimentAnalysisFragment : Fragment() {

    private var _sentimentAnalysisBinding: FragmentSentimentAnalysisBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val _binding get() = _sentimentAnalysisBinding!!
    private var _setTweet: String? = null
    private var _getTweet: TextTweet? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _sentimentAnalysisBinding =
            FragmentSentimentAnalysisBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(requireContext())
        val sentimentAnalysisViewModel =
            ViewModelProvider(this, factory)[SentimentAnalysisViewModel::class.java]

        true.shimmerLoading()
        loadTweet(sentimentAnalysisViewModel)
        loadPercentage(sentimentAnalysisViewModel)
        true.shimmerLoading()

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        /*event ketika widget dijalankan*/
        swipeRefreshLayout.setOnRefreshListener(object :
            SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refreshItem()
            }

            fun refreshItem() {
                true.shimmerLoading()
                Handler(requireActivity().mainLooper).postDelayed({
                    loadTweet(sentimentAnalysisViewModel)
                    loadPercentage(sentimentAnalysisViewModel)
                }, 500)
                onItemLoad()
            }

            fun onItemLoad() {
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun loadTweet(sentimentAnalysisViewModel: SentimentAnalysisViewModel) {
        sentimentAnalysisViewModel.tweet.observe(viewLifecycleOwner, { tweet ->
            with(_sentimentAnalysisBinding?.layoutRvTweetsPost?.rvTweet) {
                val layoutManagerVertical =
                    LinearLayoutManager(context)
                this?.layoutManager = layoutManagerVertical
                this?.setHasFixedSize(true)

                val tweetAdapter = SentimentAnalysisAdapter()
                this?.adapter = tweetAdapter

                tweet.let { tweetAdapter.setTweet(it) }
                tweetAdapter.notifyDataSetChanged()
            }
            false.shimmerLoading()
        })

        sentimentAnalysisViewModel.post.observe(viewLifecycleOwner, { post ->
            Log.d("Post Fragment", post.data.toString())
            for (i in 0 until (post.data?.size?.minus(1) ?: 0)) {
                val tweet = post.data?.get(i)?.text
                _setTweet = tweet
                _getTweet = TextTweet(_setTweet.toString())
                Log.d("post frag tweet", _setTweet.toString())

                _getTweet?.let {
                    sentimentAnalysisViewModel.getAnalysis(it)
                        .observe(viewLifecycleOwner, { sentiment ->
                            Log.d("sentiment tweet", sentiment.data?.result.toString())
                        })
                }
            }
        })

        sentimentAnalysisViewModel.profile.observe(viewLifecycleOwner, { profile ->
            Log.d("profile Fragment", profile.data.toString())
        })


    }

    private fun loadPercentage(sentimentAnalysisViewModel: SentimentAnalysisViewModel) {
        sentimentAnalysisViewModel.neutralCount.observe(viewLifecycleOwner, { neutral ->
            val neutralValue = neutral.neutral.toDouble()
            sentimentAnalysisViewModel.allSentimentCount.observe(viewLifecycleOwner, { total ->
                val neutralTotal = total.allResult.toDouble()
                val neutral1 = (neutralValue.div(neutralTotal))
                val neutralPercent = String.format("%.2f", (neutral1 * 100))
                with(_binding.itemSentimentAnalysisLayout) {
                    percentNeutralSentiment.text = StringBuilder("${neutralPercent}%")
                }
            })
        })

        sentimentAnalysisViewModel.positiveCount.observe(viewLifecycleOwner, { pro ->
            val proValue = pro.pro.toDouble()
            sentimentAnalysisViewModel.allSentimentCount.observe(viewLifecycleOwner, { total ->
                val proTotal = total.allResult.toDouble()
                val pro1 = (proValue.div(proTotal))
                val proPercent = String.format("%.2f", (pro1 * 100))
                with(_binding.itemSentimentAnalysisLayout) {
                    percentPositiveSentiment.text = StringBuilder("${proPercent}%")
                }
            })
        })

        sentimentAnalysisViewModel.negativeCount.observe(viewLifecycleOwner, { contra ->
            val contraValue = contra.contra.toDouble()
            sentimentAnalysisViewModel.allSentimentCount.observe(viewLifecycleOwner, { total ->
                val contraTotal = total.allResult.toDouble()
                with(_binding.itemSentimentAnalysisLayout) {
                    val contra1 = (contraValue.div(contraTotal))
                    val contraPercent = String.format("%.2f", (contra1 * 100))
                    percentNegativeSentiment.text = StringBuilder("${contraPercent}%")
                }
            })
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _sentimentAnalysisBinding = null
    }

    private fun Boolean.shimmerLoading() {
        if (this) {
            shimmerFrameLayout.startShimmer()
            shimmerFrameLayout.visibility = View.VISIBLE
            scrollViewLayout.visibility = View.GONE
        } else {
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            scrollViewLayout.visibility = View.VISIBLE
            Thread.sleep(750)
        }
    }
}