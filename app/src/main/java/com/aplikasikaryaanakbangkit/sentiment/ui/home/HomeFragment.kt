package com.aplikasikaryaanakbangkit.sentiment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var _homeViewModel: HomeViewModel
    private var _fragmentHomeBinding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val _binding get() = _fragmentHomeBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        val textView: TextView = _binding.textHome
        _homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentHomeBinding = null
    }
}