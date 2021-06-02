package com.aplikasikaryaanakbangkit.sentiment.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentWelcome3Binding

class WelcomeFragment3 : Fragment() {

    private var _fragmentWelcomeBinding: FragmentWelcome3Binding? = null

    private val _binding get() = _fragmentWelcomeBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentWelcomeBinding =
            FragmentWelcome3Binding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun onDestroy() {
        super.onDestroy()

        _fragmentWelcomeBinding = null
    }
}