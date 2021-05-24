package com.aplikasikaryaanakbangkit.sentiment.vaccination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentVaccinationBinding
import com.aplikasikaryaanakbangkit.sentiment.ui.statistic.VaccinationViewModel

class VaccinationFragment : Fragment() {

    private lateinit var _vaccinationViewModel: VaccinationViewModel
    private var _fragmentVaccinationBinding: FragmentVaccinationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val _binding get() = _fragmentVaccinationBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vaccinationViewModel =
            ViewModelProvider(this).get(VaccinationViewModel::class.java)

        _fragmentVaccinationBinding =
            FragmentVaccinationBinding.inflate(inflater, container, false)

        val root: View = _binding.root

        val textView: TextView = _binding.textDashboard
        _vaccinationViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentVaccinationBinding = null
    }
}