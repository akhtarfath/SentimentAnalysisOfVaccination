package com.aplikasikaryaanakbangkit.sentiment.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.remote.response.sentiment.TextTweet
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentHomeBinding
import com.aplikasikaryaanakbangkit.sentiment.news.NewsCovidAdapter
import com.aplikasikaryaanakbangkit.sentiment.news.NewsVaccineAdapter
import com.aplikasikaryaanakbangkit.sentiment.news.NewsViewModel
import com.aplikasikaryaanakbangkit.sentiment.sentiment.SentimentAnalysisViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.mini_item_covid_local_condition.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.NumberFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var _setTweet: String? = null
    private var _getTweet: TextTweet? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())

            val covidViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            val tweetViewModel = ViewModelProvider(
                    this, factory
            )[SentimentAnalysisViewModel::class.java]

            val newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

            runBlocking {
                launch {
                    delay(2000L)
                    loadTweet(tweetViewModel)
                }
                loadNews(newsViewModel)
                loadCovid(covidViewModel)
            }

            val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
            /*event ketika widget dijalankan*/
            swipeRefreshLayout.setOnRefreshListener(object :
                    OnRefreshListener {
                override fun onRefresh() {
                    refreshItem()
                }

                fun refreshItem() {
                    true.shimmerLoading()
                    Handler(requireActivity().mainLooper).postDelayed({
                        loadCovid(covidViewModel)
                        loadTweet(tweetViewModel)
                        loadNews(newsViewModel)
                    }, 500)
                    onItemLoad()
                }

                fun onItemLoad() {
                    swipeRefreshLayout.isRefreshing = false
                }
            })
        }
    }

    private fun loadCovid(covidViewModel: HomeViewModel) {
        covidViewModel.getGlobalCovid.observe(viewLifecycleOwner, { globalCovid ->
            if (globalCovid != null) {
                _binding?.covidStatistic?.covidWorldCondition?.let {
                    it.numberPositive.text = StringBuilder(
                            NumberFormat.getNumberInstance(Locale.US).format(
                                    globalCovid.data?.confirmedGlobal ?: 0
                            )
                    )
                    it.numberOfDeaths.text = StringBuilder(
                            NumberFormat.getNumberInstance(Locale.US).format(
                                    globalCovid.data?.deathGlobal ?: 0
                            )
                    )
                    it.numberOfCures.text = StringBuilder(
                            NumberFormat.getNumberInstance(Locale.US).format(
                                    globalCovid.data?.recoveredGlobal ?: 0
                            )
                    )
                }
                false.shimmerLoading()

                //share
                _binding?.covidStatistic?.covidWorldCondition?.worldConditionShare?.setOnClickListener {
                    startActivity(
                            Intent.createChooser(
                                    Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(
                                                Intent.EXTRA_TEXT,
                                                """
                                        Kasus COVID-19 terkonfirmasi di seluruh Dunia.
                                        
                                        Positif     :   ${globalCovid.data?.confirmedGlobal ?: 0}
                                        Sembuh      :   ${globalCovid.data?.recoveredGlobal ?: 0}
                                        Meninggal   :   ${globalCovid.data?.deathGlobal ?: 0}
                                    """.trimIndent()
                                        )
                                        type = "text/plain"
                                    }, null
                            )
                    )
                }
            }
        })

        covidViewModel.getIDCovid.observe(viewLifecycleOwner, { idCovid ->
            if (idCovid != null) {
                when (idCovid.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        _binding?.covidStatistic?.covidLocalCondition?.let {
                            numberPositiveID.text = StringBuilder(
                                    NumberFormat.getNumberInstance(Locale.US).format(
                                            idCovid.data?.confirmed ?: 0
                                    )
                            )
                            numberOfDeathsID.text = StringBuilder(
                                    NumberFormat.getNumberInstance(Locale.US).format(
                                            idCovid.data?.deaths ?: 0
                                    )
                            )
                            numberOfCuresID.text = StringBuilder(
                                    NumberFormat.getNumberInstance(Locale.US).format(
                                            idCovid.data?.recovered ?: 0
                                    )
                            )
                        }
                        false.shimmerLoading()

                        //share
                        _binding?.covidStatistic?.covidLocalCondition?.localConditionShare?.setOnClickListener {
                            startActivity(
                                    Intent.createChooser(
                                            Intent().apply {
                                                action = Intent.ACTION_SEND
                                                putExtra(
                                                        Intent.EXTRA_TEXT,
                                                        """
                                                Kasus COVID-19 terkonfirmasi di Indonesia.
                                                
                                                Positif     :   ${idCovid.data?.confirmed ?: 0}
                                                Sembuh      :   ${idCovid.data?.recovered ?: 0}
                                                Meninggal   :   ${idCovid.data?.deaths ?: 0}
                                            """.trimIndent()
                                                )
                                                type = "text/plain"
                                            }, null
                                    )
                            )
                        }
                    }
                    Status.ERROR -> {
                        false.shimmerLoading()
                        Toast.makeText(
                                activity?.applicationContext,
                                getString(R.string.error_msg),
                                Toast.LENGTH_SHORT
                        )
                                .show()
                    }
                }
            }
        })
    }

    private fun loadTweet(tweetViewModel: SentimentAnalysisViewModel) {
        tweetViewModel.tweet.observe(viewLifecycleOwner, { tweet ->
            with(_binding?.tweetSentiment?.includeTweet?.rvTweet) {
                val layoutManagerHorizontal =
                        LinearLayoutManager(
                                context,
                                LinearLayoutManager.HORIZONTAL,
                                false
                        )
                this?.layoutManager = layoutManagerHorizontal
                this?.setHasFixedSize(false)

                val tweetAdapter = HomeTweetAdapter()
                this?.adapter = tweetAdapter

                tweetAdapter.setTweet(tweet)
                tweetAdapter.notifyDataSetChanged()
            }
            false.shimmerLoading()
        })

        tweetViewModel.post.observe(viewLifecycleOwner, { post ->
            for (i in 0 until (post.data?.size?.minus(1) ?: 0)) {
                val tweet = post.data?.get(i)?.text
                _setTweet = tweet
                _getTweet = TextTweet(_setTweet.toString())

                _getTweet?.let {
                    tweetViewModel.getAnalysis(it).observe(viewLifecycleOwner, {
                    })
                }
            }
        })

        tweetViewModel.profile.observe(viewLifecycleOwner, {
        })
    }

    private fun loadNews(newsViewModel: NewsViewModel) {
        newsViewModel.newsCovidHeadline.observe(viewLifecycleOwner, { newsCovid ->
            if (newsCovid != null) {
                when (newsCovid.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        _binding?.covidNews?.newsActivityHorizontal?.let {
                            with(it.rvHorizontal) {
                                val layoutManagerHorizontal =
                                        LinearLayoutManager(
                                                context, LinearLayoutManager.HORIZONTAL, false
                                        )
                                this.layoutManager = layoutManagerHorizontal
                                this.setHasFixedSize(true)

                                val newsCovidAdapter = NewsCovidAdapter()
                                this.adapter = newsCovidAdapter
                                newsCovidAdapter.submitList(newsCovid.data)
                            }
                        }
                        false.shimmerLoading()
                    }
                    Status.ERROR -> {
                        false.shimmerLoading()
                        Toast.makeText(
                                activity?.applicationContext,
                                getString(R.string.error_msg),
                                Toast.LENGTH_SHORT
                        )
                                .show()
                    }
                }
            }
        })

        newsViewModel.newsVaccine.observe(viewLifecycleOwner, { newsVaccine ->
            if (newsVaccine != null) {
                when (newsVaccine.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        false.shimmerLoading()
                        _binding?.covidNews?.newsActivityVertical?.let {
                            with(it.rvVertical) {
                                val layoutManagerVertical = GridLayoutManager(context, 2)

                                this.layoutManager = layoutManagerVertical
                                this.setHasFixedSize(true)

                                val newsVaccineAdapter = NewsVaccineAdapter()
                                this.adapter = newsVaccineAdapter
                                newsVaccineAdapter.submitList(newsVaccine.data)
                            }
                        }
                    }
                    Status.ERROR -> {
                        false.shimmerLoading()
                        _binding?.viewError?.viewError?.visibility = View.VISIBLE
                        Toast.makeText(
                                activity?.applicationContext,
                                getString(R.string.error_msg),
                                Toast.LENGTH_SHORT
                        )
                                .show()
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
