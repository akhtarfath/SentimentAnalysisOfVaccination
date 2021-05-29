package com.aplikasikaryaanakbangkit.sentiment.vaccination

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentVaccinationBinding
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.NumberFormat
import java.util.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())

            loadVaccine(factory)
        }
    }

    private fun loadVaccine(factory: ViewModelFactory) {
        val vaccineViewModel = ViewModelProvider(this, factory)[VaccinationViewModel::class.java]

        vaccineViewModel.getVaccination.observe(viewLifecycleOwner, { vaccinationTarget ->
            if (vaccinationTarget != null) {
                when (vaccinationTarget.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        false.shimmerLoading()
                        Log.d("Sasaran Vaksinasi", vaccinationTarget.data.toString())
                        _binding.vaccineTarget.targetOfVaccination.let {
                            it.numberVaccineTarget.text = StringBuilder(
                                    "Total sasaran vaksin\n" +
                                            NumberFormat.getNumberInstance(Locale.US).format(
                                                    vaccinationTarget.data?.totalTargetVaccination
                                            )
                            )

                            it.numberSdmVaccinationTarget.text = StringBuilder(
                                    "SDM Kesehatan\n" +
                                            NumberFormat.getNumberInstance(Locale.US).format(
                                                    vaccinationTarget.data?.vaccinationTargetHealthHR
                                            )
                            )
                            it.numberPetugasVaccinationTarget.text = StringBuilder(
                                    "Petugas Publik\n" +
                                            NumberFormat.getNumberInstance(Locale.US).format(
                                                    vaccinationTarget.data?.vaccinationTargetPublicOfficer
                                            )
                            )

                            it.numberLansiaVaccinationTarget.text = StringBuilder(
                                    "Lansia\n" +
                                            NumberFormat.getNumberInstance(Locale.US).format(
                                                    vaccinationTarget.data?.vaccinationTargetElderly
                                            )
                            )

                            it.numberVaccine1Target.text = StringBuilder(
                                    "Vaksinasi 1\n" +
                                            NumberFormat.getNumberInstance(Locale.US).format(
                                                    vaccinationTarget.data?.vaccination1
                                            )
                            )
                            it.numberVaccine2Target.text = StringBuilder(
                                    "Vaksinasi 2\n" +
                                            NumberFormat.getNumberInstance(Locale.US).format(
                                                    vaccinationTarget.data?.vaccination2
                                            )
                            )
                        }
                    }
                    Status.ERROR -> {
                        false.shimmerLoading()
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

        vaccineViewModel.getVaccinationStepHealthHR.observe(viewLifecycleOwner) { sdm ->
            if (sdm != null) {
                false.shimmerLoading()
                Log.d("Tahapan SDM Covid", sdm.data.toString())

                _binding.vaccineStep.healthHumanResources.healthHumanResources.let {
                    it.totalVaccineSdm1.text = StringBuilder(
                            "Total Vaksin 1\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        sdm.data?.totalVaccination1 ?: 0
                                )
                            }"
                    )
                    it.totalVaccineSdm2.text = StringBuilder(
                            "Total Vaksin 2\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        sdm.data?.totalVaccination2 ?: 0
                                )
                            }"
                    )
                    it.numberVaccine1.text = StringBuilder(
                            "Sudah Vaksin 1\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        sdm.data?.vaccinated1 ?: 0
                                )
                            }"
                    )
                    it.numberVaccine2.text = StringBuilder(
                            "Sudah Vaksin 2\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        sdm.data?.vaccinated2 ?: 0
                                )
                            }"
                    )
                    it.numberDelayedVaccine1.text = StringBuilder(
                            "Tertunda Vaksin 1\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        sdm.data?.delayedVaccination1 ?: 0
                                )
                            }"
                    )
                    it.numberDelayedVaccine2.text = StringBuilder(
                            "Tertunda Vaksin 2\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        sdm.data?.delayedVaccination2 ?: 0
                                )
                            }"
                    )
                }
            }
        }

        vaccineViewModel.getVaccinationStepElderly.observe(viewLifecycleOwner) { lansia ->
            if (lansia != null) {
                false.shimmerLoading()
                Log.d("Tahapan Lansia Covid", lansia.data.toString())

                _binding.vaccineStep.healthHumanResources.theElderly.let {
                    it.totalVaccine1.text = StringBuilder(
                            "Total Vaksin 1\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        lansia.data?.totalVaccination1 ?: 0
                                )
                            }"
                    )
                    it.totalVaccine2.text = StringBuilder(
                            "Total Vaksin 2\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        lansia.data?.totalVaccination2 ?: 0
                                )
                            }"
                    )
                    it.numberVaccine1.text = StringBuilder(
                            "Sudah Vaksin 1\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        lansia.data?.vaccinated1 ?: 0
                                )
                            }"
                    )
                    it.numberVaccine2.text = StringBuilder(
                            "Sudah Vaksin 2\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        lansia.data?.vaccinated2 ?: 0
                                )
                            }"
                    )
                    it.numberDelayedVaccine1.text = StringBuilder(
                            "Tertunda Vaksin 1\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        lansia.data?.delayedVaccine1 ?: 0
                                )
                            }"
                    )
                    it.numberDelayedVaccine2.text = StringBuilder(
                            "Tertunda Vaksin 2\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        lansia.data?.delayedVaccine2 ?: 0
                                )
                            }"
                    )
                }
            }
        }

        vaccineViewModel.getVaccinationStepPublicOfficer.observe(viewLifecycleOwner) { petugas ->
            if (petugas != null) {
                false.shimmerLoading()
                Log.d("Tahapan Petugas Covid", petugas.data.toString())

                _binding.vaccineStep.healthHumanResources.publicOfficers.let {
                    it.totalVaccine1.text = StringBuilder(
                            "Total Vaksin 1\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        petugas.data?.totalVaccination1 ?: 0
                                )
                            }"
                    )
                    it.totalVaccine2.text = StringBuilder(
                            "Total Vaksin 2\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        petugas.data?.totalVaccination2 ?: 0
                                )
                            }"
                    )
                    it.numberVaccine1.text = StringBuilder(
                            "Sudah Vaksin 1\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        petugas.data?.vaccinated1 ?: 0
                                )
                            }"
                    )
                    it.numberVaccine2.text = StringBuilder(
                            "Sudah Vaksin 2\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        petugas.data?.vaccinated2 ?: 0
                                )
                            }"
                    )
                    it.numberDelayedVaccine1.text = StringBuilder(
                            "Tertunda Vaksin 1\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        petugas.data?.delayedVaccination1 ?: 0
                                )
                            }"
                    )
                    it.numberDelayedVaccine2.text = StringBuilder(
                            "Tertunda Vaksin 2\n${
                                NumberFormat.getNumberInstance(Locale.US).format(
                                        petugas.data?.delayedVaccination2 ?: 0
                                )
                            }"
                    )
                }
            }
        }

        vaccineViewModel.getVaccinationCoverage.observe(viewLifecycleOwner) { cakupanVaksinasi ->
            if (cakupanVaksinasi != null) {
                when (cakupanVaksinasi.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        false.shimmerLoading()
                        Log.d("Cakupan Vaksinasi", cakupanVaksinasi.data.toString())

                        _binding.vaccineCoverage.targetOfVaccination.let {
                            it.vaccination1.text = StringBuilder(
                                    "Vaksinasi 1\n${cakupanVaksinasi.data?.vaccination1.toString()}"
                            )
                            it.vaccination2.text = StringBuilder(
                                    "Vaksinasi 2\n${cakupanVaksinasi.data?.vaccination2.toString()}"
                            )
                            it.vaccinationSDM1.text = StringBuilder(
                                    "SDM Kesehatan.\nVaksinasi 1. " +
                                            cakupanVaksinasi.data?.healthHRVaccination1.toString()
                            )
                            it.vaccinationSDM2.text = StringBuilder(
                                    "SDM Kesehatan.\nVaksinasi 2. " +
                                            cakupanVaksinasi.data?.healthHRVaccination2.toString()
                            )
                            it.vaccinationPetugas1.text = StringBuilder(
                                    "Petugas Publik.\nVaksinasi 1. " +
                                            cakupanVaksinasi.data?.publicOfficerVaccination1.toString()
                            )
                            it.vaccinationPetugas2.text = StringBuilder(
                                    "Petugas Publik.\nVaksinasi 2. " +
                                            cakupanVaksinasi.data?.publicOfficerVaccination2.toString()
                            )
                            it.numberTheElder1.text = StringBuilder(
                                    "Lansia. Vaksinasi 1\n" +
                                            cakupanVaksinasi.data?.elderlyVaccination1.toString()
                            )
                            it.numberTheElder2.text = StringBuilder(
                                    "Lansia. Vaksinasi 2\n" +
                                            cakupanVaksinasi.data?.elderlyVaccination2.toString()
                            )
                        }
                    }
                    Status.ERROR -> {
                        false.shimmerLoading()
                        Toast.makeText(
                                activity?.applicationContext,
                                getString(R.string.error_msg),
                                Toast.LENGTH_SHORT
                        )
                                .show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentVaccinationBinding = null
    }

    private fun Boolean.shimmerLoading() {
        if (this) {
            shimmerFrameLayout.startShimmer()
            shimmerFrameLayout.visibility = View.VISIBLE
            scrollViewLayout.visibility = View.GONE
        } else {
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            scrollViewLayout.visibility = View.VISIBLE
            Thread.sleep(750)
        }
    }
}