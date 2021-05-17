package com.aplikasikaryaanakbangkit.sentiment.ui.sentimentanalysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        _sentimentAnalysisViewModel =
            ViewModelProvider(this).get(SentimentAnalysisViewModel::class.java)

        _sentimentAnalysisBinding =
            FragmentSentimentAnalysisBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        val textView: TextView = _binding.textNotifications
        _sentimentAnalysisViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _sentimentAnalysisBinding = null
    }
}