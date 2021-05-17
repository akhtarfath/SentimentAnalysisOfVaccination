package com.aplikasikaryaanakbangkit.sentiment.ui.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentStatisticBinding

class StatisticFragment : Fragment() {

    private lateinit var _statisticViewModel: StatisticViewModel
    private var _fragmentStatisticBinding: FragmentStatisticBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val _binding get() = _fragmentStatisticBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _statisticViewModel =
            ViewModelProvider(this).get(StatisticViewModel::class.java)

        _fragmentStatisticBinding = FragmentStatisticBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        val textView: TextView = _binding.textDashboard
        _statisticViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentStatisticBinding = null
    }
}