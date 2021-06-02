package com.aplikasikaryaanakbangkit.sentiment.welcome

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aplikasikaryaanakbangkit.sentiment.MainActivity
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

        with(_fragmentWelcomeBinding){
            this?.start?.setOnClickListener {
                startActivity(Intent(context, MainActivity::class.java))
                activity?.finish()
            }
            this?.skip?.setOnClickListener {
                startActivity(Intent(context, MainActivity::class.java))
                activity?.finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _fragmentWelcomeBinding = null
    }
}