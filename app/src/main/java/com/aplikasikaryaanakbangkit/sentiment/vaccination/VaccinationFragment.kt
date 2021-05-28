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
import com.airbnb.lottie.LottieAnimationView
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentVaccinationBinding
import kotlinx.android.synthetic.main.mini_item_vaccine_target.*

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

        true.loading()
        vaccineViewModel.getVaccination.observe(viewLifecycleOwner, { sasaranVaksinasi ->
            if (sasaranVaksinasi != null) {
                when (sasaranVaksinasi.status) {
                    Status.LOADING -> true.loading()
                    Status.SUCCESS -> {
                        false.loading()
                        Log.d("Sasaran Vaksinasi", sasaranVaksinasi.data.toString())
                        _binding.vaccineTarget.targetOfVaccination.let {
                            it.numberVaccineTarget.text = sasaranVaksinasi.data?.totalSasaranVaksinasi.toString()
                            it.numberSdmVaccinationTarget.text = sasaranVaksinasi.data?.sasaranVaksinasiSdmk.toString()
                            it.numberPetugasVaccinationTarget.text = sasaranVaksinasi.data?.sasaranVaksinasiPetugasPublik.toString()
                            it.numberLansiaVaccinationTarget.text = sasaranVaksinasi.data?.sasaranVaksinasiLansia.toString()
                            it.numberVaccine1Target.text = sasaranVaksinasi.data?.vaksinasi1.toString()
                            it.numberVaccine2Target.text = sasaranVaksinasi.data?.vaksinasi2.toString()
                        }
                    }
                    Status.ERROR -> {
                        false.loading()
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
            if(sdm != null){
                false.loading()
                Log.d("Tahapan SDM Covid", sdm.data.toString())

                _binding.vaccineStep.healthHumanResources.healthHumanResources.let {
                    it.totalVaccineSdm1.text = sdm.data?.totalVaksinasi1SDM.toString()
                    it.totalVaccineSdm2.text = sdm.data?.totalVaksinasi2SDM.toString()
                    it.numberVaccine1.text = sdm.data?.sudahVaksin1SDM.toString()
                    it.numberVaccine2.text = sdm.data?.sudahVaksin2SDM.toString()
                    it.numberDelayedVaccine1.text = sdm.data?.tertundaVaksin1SDM.toString()
                    it.numberDelayedVaccine2.text = sdm.data?.tertundaVaksin2SDM.toString()
                }
            }
        })

        vaccineViewModel.getTahapanLansia.observe(viewLifecycleOwner, { lansia ->
            if(lansia != null){
                false.loading()
                Log.d("Tahapan Lansia Covid", lansia.data.toString())

                _binding.vaccineStep.healthHumanResources.theElderly.let {
                    it.totalVaccine1.text = lansia.data?.totalVaksinasi1.toString()
                    it.totalVaccine2.text = lansia.data?.totalVaksinasi2.toString()
                    it.numberVaccine1.text = lansia.data?.sudahVaksin1.toString()
                    it.numberVaccine2.text = lansia.data?.sudahVaksin2.toString()
                    it.numberDelayedVaccine1.text = lansia.data?.tertundaVaksin1.toString()
                    it.numberDelayedVaccine2.text = lansia.data?.tertundaVaksin2.toString()
                }
            }
        })

        vaccineViewModel.getTahapanPetugas.observe(viewLifecycleOwner, { petugas ->
            if(petugas != null){
                false.loading()
                Log.d("Tahapan Petugas Covid", petugas.data.toString())

                _binding.vaccineStep.healthHumanResources.publicOfficers.let {
                    it.totalVaccine1.text = petugas.data?.totalVaksinasi1PP.toString()
                    it.totalVaccine2.text = petugas.data?.totalVaksinasi2PP.toString()
                    it.numberVaccine1.text = petugas.data?.sudahVaksin1PP.toString()
                    it.numberVaccine2.text = petugas.data?.sudahVaksin2PP.toString()
                    it.numberDelayedVaccine1.text = petugas.data?.tertundaVaksin1PP.toString()
                    it.numberDelayedVaccine2.text = petugas.data?.tertundaVaksin2PP.toString()
                }
            }
        })

        vaccineViewModel.getCakupanVaccination.observe(viewLifecycleOwner, { cakupanVaksinasi ->
            if (cakupanVaksinasi != null) {
                when (cakupanVaksinasi.status) {
                    Status.LOADING -> true.loading()
                    Status.SUCCESS -> {
                        false.loading()
                        Log.d("Cakupan Vaksinasi", cakupanVaksinasi.data.toString())
                        _binding.vaccineCoverage.targetOfVaccination.let {
                            it.vaccination1.text = cakupanVaksinasi.data?.vaksinasi1.toString()
                            it.vaccination2.text = cakupanVaksinasi.data?.vaksinasi2.toString()
                            it.vaccinationPetugas1.text = cakupanVaksinasi.data?.petugasPublikVaksinasi1.toString()
                            it.vaccinationPetugas2.text = cakupanVaksinasi.data?.petugasPublikVaksinasi2.toString()
                            it.vaccinationSDM1.text = cakupanVaksinasi.data?.sdmKesehatanVaksinasi1.toString()
                            it.vaccinationSDM2.text = cakupanVaksinasi.data?.sdmKesehatanVaksinasi2.toString()
                            it.numberTheElder1.text = cakupanVaksinasi.data?.lansiaVaksinasi1.toString()
                            it.numberTheElder2.text = cakupanVaksinasi.data?.lansiaVaksinasi2.toString()
                        }
                    }
                    Status.ERROR -> {
                        false.loading()
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

    private fun Boolean.loading() {
        val progressBar = view?.findViewById<LottieAnimationView>(R.id.progressBar)

        if (this) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }
}