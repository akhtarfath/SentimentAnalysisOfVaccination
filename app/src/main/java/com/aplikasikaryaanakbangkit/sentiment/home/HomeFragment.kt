package com.aplikasikaryaanakbangkit.sentiment.home

import android.os.Bundle
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

class HomeFragment : Fragment() {

    private lateinit var _newsViewModel: NewsViewModel

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

            loadNews(factory)
        }
    }

    private fun loadNews(factory: ViewModelFactory) {
        _newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        val newsCovidAdapter = NewsCovidAdapter()
        val newsVaccineAdapter = NewsVaccineAdapter()

        true.loading()
        _newsViewModel.newsHeadline.observe(viewLifecycleOwner, { newsCovid ->
            if (newsCovid != null) {
                when (newsCovid.status) {
                    Status.LOADING -> true.loading()
                    Status.SUCCESS -> {
                        false.loading()
                        newsCovidAdapter.submitList(newsCovid.data)
                    }
                    Status.ERROR -> {
                        false.loading()
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
        _binding?.covidNews?.newsActivityHorizontal?.let {
            with(it.rvHorizontal) {
                val layoutManagerHorizontal =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                this.layoutManager = layoutManagerHorizontal
                this.setHasFixedSize(true)
                this.adapter = newsCovidAdapter
            }
        }

        _newsViewModel.newsLatest.observe(viewLifecycleOwner, { newsVaccine ->
            if (newsVaccine != null) {
                when (newsVaccine.status) {
                    Status.LOADING -> true.loading()
                    Status.SUCCESS -> {
                        false.loading()
                        newsVaccineAdapter.submitList(newsVaccine.data)
                    }
                    Status.ERROR -> {
                        false.loading()
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
        _binding?.covidNews?.newsActivityVertical?.let {
            with(it.rvVertical) {
                val layoutManagerVertical = LinearLayoutManager(context)

                this.layoutManager = layoutManagerVertical
                this.setHasFixedSize(true)
                this.adapter = newsVaccineAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Boolean.loading() {
        if (this) {
            _binding?.progressBar?.visibility ?: View.VISIBLE
        } else {
            _binding?.progressBar?.visibility ?: View.GONE
        }
    }
}
