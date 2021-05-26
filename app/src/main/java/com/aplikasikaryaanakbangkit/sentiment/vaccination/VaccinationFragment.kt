package com.aplikasikaryaanakbangkit.sentiment.vaccination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentVaccinationBinding

class VaccinationFragment : Fragment() {

    private var _fragmentVaccinationBinding: FragmentVaccinationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val _binding get() = _fragmentVaccinationBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentVaccinationBinding =
            FragmentVaccinationBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentVaccinationBinding = null
    }
}