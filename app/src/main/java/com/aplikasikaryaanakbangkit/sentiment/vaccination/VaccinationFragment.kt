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

        vaccineViewModel.getVaccination.observe(viewLifecycleOwner, { sasaranVaksinasi ->
            if (sasaranVaksinasi != null) {
                when (sasaranVaksinasi.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        false.shimmerLoading()
                        Log.d("Sasaran Vaksinasi", sasaranVaksinasi.data.toString())
                        _binding.vaccineTarget.targetOfVaccination.let {
                            it.numberVaccineTarget.text = StringBuilder(
                                "Total sasaran vaksin\n" +
                                        NumberFormat.getNumberInstance(Locale.US).format(
                                            sasaranVaksinasi.data?.totalSasaranVaksinasi
                                        )
                            )

                            it.numberSdmVaccinationTarget.text = StringBuilder(
                                "SDM Kesehatan\n" +
                                        NumberFormat.getNumberInstance(Locale.US).format(
                                            sasaranVaksinasi.data?.sasaranVaksinasiSdmk
                                        )
                            )
                            it.numberPetugasVaccinationTarget.text = StringBuilder(
                                "Petugas Publik\n" +
                                        NumberFormat.getNumberInstance(Locale.US).format(
                                            sasaranVaksinasi.data?.sasaranVaksinasiPetugasPublik
                                        )
                            )

                            it.numberLansiaVaccinationTarget.text = StringBuilder(
                                "Lansia\n" +
                                        NumberFormat.getNumberInstance(Locale.US).format(
                                            sasaranVaksinasi.data?.sasaranVaksinasiLansia
                                        )
                            )

                            it.numberVaccine1Target.text = StringBuilder(
                                "Vaksinasi 1\n" +
                                        NumberFormat.getNumberInstance(Locale.US).format(
                                            sasaranVaksinasi.data?.vaksinasi1
                                        )
                            )
                            it.numberVaccine2Target.text = StringBuilder(
                                "Vaksinasi 2\n" +
                                        NumberFormat.getNumberInstance(Locale.US).format(
                                            sasaranVaksinasi.data?.vaksinasi2
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

        vaccineViewModel.getTahapanSdm.observe(viewLifecycleOwner, { sdm ->
            if (sdm != null) {
                false.shimmerLoading()
                Log.d("Tahapan SDM Covid", sdm.data.toString())

                _binding.vaccineStep.healthHumanResources.healthHumanResources.let {
                    it.totalVaccineSdm1.text = StringBuilder(
                        "Total Vaksin 1\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                sdm.data?.totalVaksinasi1SDM ?: 0
                            )}"
                    )
                    it.totalVaccineSdm2.text = StringBuilder(
                        "Total Vaksin 2\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                sdm.data?.totalVaksinasi2SDM ?: 0
                            )}"
                    )
                    it.numberVaccine1.text = StringBuilder(
                        "Sudah Vaksin 1\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                sdm.data?.sudahVaksin1SDM ?: 0
                            )}"
                    )
                    it.numberVaccine2.text = StringBuilder(
                        "Sudah Vaksin 2\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                sdm.data?.sudahVaksin2SDM ?: 0
                            )}"
                    )
                    it.numberDelayedVaccine1.text = StringBuilder(
                        "Tertunda Vaksin 1\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                sdm.data?.tertundaVaksin1SDM ?: 0
                            )}"
                    )
                    it.numberDelayedVaccine2.text = StringBuilder(
                        "Tertunda Vaksin 2\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                sdm.data?.tertundaVaksin2SDM ?: 0
                            )}"
                    )
                }
            }
        })

        vaccineViewModel.getTahapanLansia.observe(viewLifecycleOwner, { lansia ->
            if (lansia != null) {
                false.shimmerLoading()
                Log.d("Tahapan Lansia Covid", lansia.data.toString())

                _binding.vaccineStep.healthHumanResources.theElderly.let {
                    it.totalVaccine1.text = StringBuilder(
                        "Total Vaksin 1\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                lansia.data?.totalVaksinasi1 ?: 0
                            )}"
                    )
                    it.totalVaccine2.text = StringBuilder(
                        "Total Vaksin 2\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                lansia.data?.totalVaksinasi2 ?: 0
                            )}"
                    )
                    it.numberVaccine1.text = StringBuilder(
                        "Sudah Vaksin 1\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                lansia.data?.sudahVaksin1 ?: 0
                            )}"
                    )
                    it.numberVaccine2.text = StringBuilder(
                        "Sudah Vaksin 2\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                lansia.data?.sudahVaksin2 ?: 0
                            )}"
                    )
                    it.numberDelayedVaccine1.text = StringBuilder(
                        "Tertunda Vaksin 1\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                lansia.data?.tertundaVaksin1 ?: 0
                            )}"
                    )
                    it.numberDelayedVaccine2.text = StringBuilder(
                        "Tertunda Vaksin 2\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                lansia.data?.tertundaVaksin2 ?: 0
                            )}"
                    )
                }
            }
        })

        vaccineViewModel.getTahapanPetugas.observe(viewLifecycleOwner, { petugas ->
            if (petugas != null) {
                false.shimmerLoading()
                Log.d("Tahapan Petugas Covid", petugas.data.toString())

                _binding.vaccineStep.healthHumanResources.publicOfficers.let {
                    it.totalVaccine1.text = StringBuilder(
                        "Total Vaksin 1\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                petugas.data?.totalVaksinasi1PP ?: 0
                            )}"
                    )
                    it.totalVaccine2.text = StringBuilder(
                        "Total Vaksin 2\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                petugas.data?.totalVaksinasi2PP ?: 0
                            )}"
                    )
                    it.numberVaccine1.text = StringBuilder(
                        "Sudah Vaksin 1\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                petugas.data?.sudahVaksin1PP ?: 0
                            )}"
                    )
                    it.numberVaccine2.text = StringBuilder(
                        "Sudah Vaksin 2\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                petugas.data?.sudahVaksin2PP ?: 0
                            )}"
                    )
                    it.numberDelayedVaccine1.text = StringBuilder(
                        "Tertunda Vaksin 1\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                petugas.data?.tertundaVaksin1PP ?: 0
                            )}"
                    )
                    it.numberDelayedVaccine2.text = StringBuilder(
                        "Tertunda Vaksin 2\n${
                            NumberFormat.getNumberInstance(Locale.US).format(
                                petugas.data?.tertundaVaksin2PP ?: 0
                            )}"
                    )
                }
            }
        })

        vaccineViewModel.getCakupanVaccination.observe(viewLifecycleOwner, { cakupanVaksinasi ->
            if (cakupanVaksinasi != null) {
                when (cakupanVaksinasi.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        false.shimmerLoading()
                        Log.d("Cakupan Vaksinasi", cakupanVaksinasi.data.toString())

                        _binding.vaccineCoverage.targetOfVaccination.let {
                            it.vaccination1.text = StringBuilder(
                                "Vaksinasi 1\n${cakupanVaksinasi.data?.vaksinasi1.toString()}"
                            )
                            it.vaccination2.text = StringBuilder(
                                "Vaksinasi 2\n${cakupanVaksinasi.data?.vaksinasi2.toString()}"
                            )
                            it.vaccinationSDM1.text = StringBuilder(
                                "SDM Kesehatan.\nVaksinasi 1. " +
                                        cakupanVaksinasi.data?.sdmKesehatanVaksinasi1.toString()
                            )
                            it.vaccinationSDM2.text = StringBuilder(
                                "SDM Kesehatan.\nVaksinasi 2. " +
                                        cakupanVaksinasi.data?.sdmKesehatanVaksinasi2.toString()
                            )
                            it.vaccinationPetugas1.text = StringBuilder(
                                "Petugas Publik.\nVaksinasi 1. " +
                                        cakupanVaksinasi.data?.petugasPublikVaksinasi1.toString()
                            )
                            it.vaccinationPetugas2.text = StringBuilder(
                                "Petugas Publik.\nVaksinasi 2. " +
                                        cakupanVaksinasi.data?.petugasPublikVaksinasi2.toString()
                            )
                            it.numberTheElder1.text = StringBuilder(
                                "Lansia. Vaksinasi 1\n" +
                                        cakupanVaksinasi.data?.lansiaVaksinasi1.toString()
                            )
                            it.numberTheElder2.text = StringBuilder(
                                "Lansia. Vaksinasi 2\n" +
                                        cakupanVaksinasi.data?.lansiaVaksinasi2.toString()
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