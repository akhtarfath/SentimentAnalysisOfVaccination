package com.aplikasikaryaanakbangkit.sentiment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentHomeBinding
import com.aplikasikaryaanakbangkit.sentiment.news.NewsCovidAdapter
import com.aplikasikaryaanakbangkit.sentiment.news.NewsVaccineAdapter
import com.aplikasikaryaanakbangkit.sentiment.news.NewsViewModel
import com.aplikasikaryaanakbangkit.sentiment.sentiment.SentimentAnalysisAdapter
import com.aplikasikaryaanakbangkit.sentiment.sentiment.SentimentAnalysisViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.mini_item_covid_local_condition.*
import java.text.NumberFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

            loadCovid(factory)
            loadTweet(factory)
            loadNews(factory)
        }
    }

    private fun loadCovid(factory: ViewModelFactory) {
        val covidViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        covidViewModel.getGlobalCovid.observe(viewLifecycleOwner, { globalCovid ->
            if (globalCovid != null) {
                _binding?.covidStatistic?.covidWorldCondition?.let {
                    it.numberPositive.text = StringBuilder(
                        "Positif \n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                globalCovid.data?.confirmedGlobal ?: 0
                            )
                        }"
                    )
                    it.numberOfDeaths.text = StringBuilder(
                        "Meninggal \n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                globalCovid.data?.deathGlobal ?: 0
                            )
                        }"
                    )
                    it.numberOfCures.text = StringBuilder(
                        "Sembuh \n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                globalCovid.data?.recoveredGlobal ?: 0
                            )
                        }"
                    )
                }
                false.shimmerLoading()
            }
        })

        covidViewModel.getIDCovid.observe(viewLifecycleOwner, { idCovid ->
            if (idCovid != null) {
                when (idCovid.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        Log.d("ID Covid", idCovid.data.toString())
                        _binding?.covidStatistic?.covidLocalCondition?.let {
                            numberPositiveID.text = StringBuilder(
                                "Positif \n${
                                    NumberFormat.getNumberInstance(Locale.US).format(
                                        idCovid.data?.confirmed ?: 0
                                    )
                                }"
                            )
                            numberOfDeathsID.text = StringBuilder(
                                "Meninggal \n${
                                    NumberFormat.getNumberInstance(Locale.US).format(
                                        idCovid.data?.deaths ?: 0
                                    )
                                }"
                            )
                            numberOfCuresID.text = StringBuilder(
                                "Sembuh \n${
                                    NumberFormat.getNumberInstance(Locale.US).format(
                                        idCovid.data?.recovered ?: 0
                                    )
                                }"
                            )
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
    }

    private fun loadTweet(factory: ViewModelFactory) {
        val tweetViewModel =
            ViewModelProvider(this, factory)[SentimentAnalysisViewModel::class.java]

        tweetViewModel.getTweet().observe(viewLifecycleOwner, { tweet ->
            with(_binding?.tweetSentiment?.includeTweet?.rvTweet) {
                val layoutManagerHorizontal =
                    LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                this?.layoutManager = layoutManagerHorizontal
                this?.setHasFixedSize(true)

                val tweetAdapter = SentimentAnalysisAdapter()
                this?.adapter = tweetAdapter

                tweetAdapter.setTweet(tweet)
                tweetAdapter.notifyDataSetChanged()
            }
            false.shimmerLoading()
        })

        tweetViewModel.getPost().observe(viewLifecycleOwner, { post ->
            Log.d("Post Fragment", post.data.toString())
        })

        tweetViewModel.getProfile().observe(viewLifecycleOwner, { profile ->
            Log.d("Profile Fragment", profile.data.toString())
        })
    }

    private fun loadNews(factory: ViewModelFactory) {
        val newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        true.shimmerLoading()
        newsViewModel.newsHeadline.observe(viewLifecycleOwner, { newsCovid ->
            if (newsCovid != null) {
                when (newsCovid.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        _binding?.covidNews?.newsActivityHorizontal?.let {
                            with(it.rvHorizontal) {
                                val layoutManagerHorizontal =
                                    LinearLayoutManager(
                                        context,
                                        LinearLayoutManager.HORIZONTAL,
                                        false
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

        newsViewModel.newsLatest.observe(viewLifecycleOwner, { newsVaccine ->
            if (newsVaccine != null) {
                when (newsVaccine.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        false.shimmerLoading()
                        _binding?.covidNews?.newsActivityVertical?.let {
                            with(it.rvVertical) {
                                val layoutManagerVertical = LinearLayoutManager(context)

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
