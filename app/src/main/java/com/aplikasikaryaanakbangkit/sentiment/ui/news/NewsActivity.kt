package com.aplikasikaryaanakbangkit.sentiment.ui.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.databinding.ActivityNewsBinding
import com.aplikasikaryaanakbangkit.sentiment.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.vo.Status

class NewsActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsBinding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(newsBinding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        val newsCovidAdapter = NewsCovidAdapter()
        val newsVaccineAdapter = NewsVaccineAdapter()

        loading(true)

        newsViewModel.getDataCovidHeadlines().observe(this, { newsCovid ->
            if (newsCovid != null) {
                when (newsCovid.status) {
                    Status.LOADING -> loading(true)
                    Status.SUCCESS -> {
                        loading(false)
                        newsCovidAdapter.submitList(newsCovid.data)
                    }
                    Status.ERROR -> {
                        loading(false)
                        Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
        with(newsBinding.newsActivityHorizontal.rvHorizontal) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsCovidAdapter
        }

        newsViewModel.getDataVaccineNews().observe(this, { newsVaccine ->
            if (newsVaccine != null) {
                when (newsVaccine.status) {
                    Status.LOADING -> loading(true)
                    Status.SUCCESS -> {
                        loading(false)
                        newsVaccineAdapter.submitList(newsVaccine.data)
                    }
                    Status.ERROR -> {
                        loading(false)
                        Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
        with(newsBinding.newsActivityVertical.rvVertical) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = newsVaccineAdapter
        }
    }

    private fun loading(state: Boolean) {
        if (state) newsBinding.progressBar.visibility = View.VISIBLE
        else newsBinding.progressBar.visibility = View.GONE
    }
}