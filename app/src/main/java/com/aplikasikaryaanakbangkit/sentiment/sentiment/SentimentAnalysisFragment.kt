package com.aplikasikaryaanakbangkit.sentiment.sentiment

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
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentSentimentAnalysisBinding

class SentimentAnalysisFragment : Fragment() {

    private lateinit var _sentimentAnalysisViewModel: SentimentAnalysisViewModel
    private var _sentimentAnalysisBinding: FragmentSentimentAnalysisBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val _binding get() = _sentimentAnalysisBinding!!

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
        _sentimentAnalysisViewModel = ViewModelProvider(this, factory)[SentimentAnalysisViewModel::class.java]

        val tweetAdapter = SentimentAnalysisAdapter()

        _sentimentAnalysisViewModel.allTweets.observe(viewLifecycleOwner, { tweet ->
            if (tweet != null) {
                when (tweet.status) {
                    Status.LOADING -> true.loading()
                    Status.SUCCESS -> {
                        false.loading()
                        tweetAdapter.submitList(tweet.data)
                    }
                    Status.ERROR -> {
                        false.loading()
                        _binding.viewError.viewError.visibility = View.VISIBLE
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

        with(_sentimentAnalysisBinding?.layoutRvTweetsPost?.rvTweet) {
            val layoutManagerVertical =
                    LinearLayoutManager(context)
            this?.layoutManager = layoutManagerVertical
            this?.setHasFixedSize(true)
            this?.adapter = tweetAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _sentimentAnalysisBinding = null
    }

    private fun Boolean.loading() {
        if (this) {
            _sentimentAnalysisBinding?.progressBar?.visibility ?: View.VISIBLE
        } else {
            _sentimentAnalysisBinding?.progressBar?.visibility ?: View.GONE
        }
    }
}