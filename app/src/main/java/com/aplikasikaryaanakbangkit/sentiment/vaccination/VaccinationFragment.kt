package com.aplikasikaryaanakbangkit.sentiment.vaccination

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
            val vaccineViewModel =
                    ViewModelProvider(this, factory)[VaccinationViewModel::class.java]

            loadVaccine(vaccineViewModel)

            val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
            /*event ketika widget dijalankan*/
            swipeRefreshLayout.setOnRefreshListener(object :
                    SwipeRefreshLayout.OnRefreshListener {
                override fun onRefresh() {
                    refreshItem()
                }

                fun refreshItem() {
                    true.shimmerLoading()
                    Handler(requireActivity().mainLooper).postDelayed({

                        loadVaccine(vaccineViewModel)
                    }, 500)
                    onItemLoad()
                }

                fun onItemLoad() {
                    swipeRefreshLayout.isRefreshing = false
                }
            })
        }
    }

    private fun loadVaccine(vaccineViewModel: VaccinationViewModel) {
        vaccineViewModel.getVaccination.observe(viewLifecycleOwner, { vaccinationTarget ->
            if (vaccinationTarget != null) {
                when (vaccinationTarget.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
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
                        false.shimmerLoading()

                        //share
                        _binding.vaccineTarget.vaccineTargetShare.setOnClickListener {
                            startActivity(
                                    Intent.createChooser(
                                            Intent().apply {
                                                action = Intent.ACTION_SEND
                                                putExtra(
                                                        Intent.EXTRA_TEXT,
                                                        """
                                                Sasaran Vaksinasi terkonfirmasi di Indonesia.
                                                
                                                Total Sasaran Vaksin     :   ${vaccinationTarget.data?.totalTargetVaccination ?: 0} Jiwa
                                                Vaksinasi 1              :   ${vaccinationTarget.data?.vaccination1 ?: 0} Jiwa
                                                Vaksinasi 2              :   ${vaccinationTarget.data?.vaccination2 ?: 0} Jiwa
                                                Vaksinasi SDM Kesehatan  :   ${vaccinationTarget.data?.vaccination2 ?: 0} Jiwa
                                                Vaksinasi Petugas Publik :   ${vaccinationTarget.data?.vaccination2 ?: 0} Jiwa
                                                Vaksinasi Lansia         :   ${vaccinationTarget.data?.vaccination2 ?: 0} Jiwa
                                            """.trimIndent()
                                                )
                                                type = "text/plain"
                                            }, null
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
                false.shimmerLoading()
            }


            vaccineViewModel.getVaccinationStepElderly.observe(viewLifecycleOwner) { lansia ->
                if (lansia != null) {
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
                    false.shimmerLoading()
                }


                vaccineViewModel.getVaccinationStepPublicOfficer.observe(viewLifecycleOwner) { petugas ->
                    if (petugas != null) {
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
                        false.shimmerLoading()
                    }

                    _binding.vaccineStep.vaccineStepShare.setOnClickListener {
                        startActivity(
                                Intent.createChooser(
                                        Intent().apply {
                                            action = Intent.ACTION_SEND
                                            putExtra(
                                                    Intent.EXTRA_TEXT,
                                                    """
                                                Tahapan Vaksinasi untuk SDM Kesehatan di Indonesia.
                                                Total Vaksinasi 1    :   ${sdm.data?.totalVaccination1 ?: 0} Jiwa
                                                Total Vaksinasi 2    :   ${sdm.data?.totalVaccination2 ?: 0} Jiwa
                                                Sudah Vaksinasi 1    :   ${sdm.data?.vaccinated1 ?: 0} Jiwa
                                                Sudah Vaksinasi 2    :   ${sdm.data?.vaccinated2 ?: 0} Jiwa
                                                Tertunda Vaksinasi 1 :   ${sdm.data?.delayedVaccination1 ?: 0} Jiwa
                                                Tertunda Vaksinasi 2 :   ${sdm.data?.delayedVaccination2 ?: 0} Jiwa
                                                
                                                Tahapan Vaksinasi untuk Lansia di Indonesia.
                                                Total Vaksinasi 1    :   ${lansia.data?.totalVaccination1 ?: 0} Jiwa
                                                Total Vaksinasi 2    :   ${lansia.data?.totalVaccination2 ?: 0} Jiwa
                                                Sudah Vaksinasi 1    :   ${lansia.data?.vaccinated1 ?: 0} Jiwa
                                                Sudah Vaksinasi 2    :   ${lansia.data?.vaccinated2 ?: 0} Jiwa
                                                Tertunda Vaksinasi 1 :   ${lansia.data?.delayedVaccine1 ?: 0} Jiwa
                                                Tertunda Vaksinasi 2 :   ${lansia.data?.delayedVaccine2 ?: 0} Jiwa
                                                
                                                Tahapan Vaksinasi untuk Petugas Publik di Indonesia.
                                                Total Vaksinasi 1    :   ${petugas.data?.totalVaccination1 ?: 0} Jiwa
                                                Total Vaksinasi 2    :   ${petugas.data?.totalVaccination2 ?: 0} Jiwa
                                                Sudah Vaksinasi 1    :   ${petugas.data?.vaccinated1 ?: 0} Jiwa
                                                Sudah Vaksinasi 2    :   ${petugas.data?.vaccinated2 ?: 0} Jiwa
                                                Tertunda Vaksinasi 1 :   ${petugas.data?.delayedVaccination1 ?: 0} Jiwa
                                                Tertunda Vaksinasi 2 :   ${petugas.data?.delayedVaccination2 ?: 0} Jiwa
                                            """.trimIndent()
                                            )
                                            type = "text/plain"
                                        }, null
                                )
                        )
                    }
                }
            }
        }

        vaccineViewModel.getVaccinationCoverage.observe(viewLifecycleOwner) { cakupanVaksinasi ->
            if (cakupanVaksinasi != null) {
                when (cakupanVaksinasi.status) {
                    Status.LOADING -> true.shimmerLoading()
                    Status.SUCCESS -> {
                        Log.d("Cakupan Vaksinasi", cakupanVaksinasi.data.toString())

                        _binding.vaccineCoverage.targetOfVaccination.let {
                            it.vaccination1.text = StringBuilder(
                                    "Vaksinasi 1\n${cakupanVaksinasi.data?.vaccination1.toString()}"
                            )
                            it.vaccination2.text = StringBuilder(
                                    "Vaksinasi 2\n${cakupanVaksinasi.data?.vaccination2.toString()}"
                            )
                            it.vaccinationSDM1.text = StringBuilder(
                                    "SDM Kesehatan\nVaksinasi 1 " +
                                            cakupanVaksinasi.data?.healthHRVaccination1.toString()
                            )
                            it.vaccinationSDM2.text = StringBuilder(
                                    "SDM Kesehatan\nVaksinasi 2 " +
                                            cakupanVaksinasi.data?.healthHRVaccination2.toString()
                            )
                            it.vaccinationPetugas1.text = StringBuilder(
                                    "Petugas Publik\nVaksinasi 1 " +
                                            cakupanVaksinasi.data?.publicOfficerVaccination1.toString()
                            )
                            it.vaccinationPetugas2.text = StringBuilder(
                                    "Petugas Publik\nVaksinasi 2 " +
                                            cakupanVaksinasi.data?.publicOfficerVaccination2.toString()
                            )
                            it.numberTheElder1.text = StringBuilder(
                                    "Lansia Vaksinasi 1\n" +
                                            cakupanVaksinasi.data?.elderlyVaccination1.toString()
                            )
                            it.numberTheElder2.text = StringBuilder(
                                    "Lansia Vaksinasi 2\n" +
                                            cakupanVaksinasi.data?.elderlyVaccination2.toString()
                            )
                        }
                        false.shimmerLoading()

                        _binding.vaccineCoverage.vaccineTargetShare.setOnClickListener {
                            startActivity(
                                    Intent.createChooser(
                                            Intent().apply {
                                                action = Intent.ACTION_SEND
                                                putExtra(
                                                        Intent.EXTRA_TEXT,
                                                        """
                                                Persentase Cakupan Pelaksanaan Vaksinasi di Indonesia.
                                                
                                                Vaksinasi 1                :   ${cakupanVaksinasi.data?.vaccination1 ?: 0}
                                                Vaksinasi 2                :   ${cakupanVaksinasi.data?.vaccination2 ?: 0}
                                                SDM Kesehatan Vaksinasi 1  :   ${cakupanVaksinasi.data?.healthHRVaccination1 ?: 0}
                                                SDM Kesehatan Vaksinasi 2  :   ${cakupanVaksinasi.data?.healthHRVaccination2 ?: 0}
                                                Petugas Publik Vaksinasi 1 :   ${cakupanVaksinasi.data?.publicOfficerVaccination1 ?: 0}
                                                Petugas Publik Vaksinasi 2 :   ${cakupanVaksinasi.data?.publicOfficerVaccination2 ?: 0}
                                                Lansia Vaksinasi 1         :   ${cakupanVaksinasi.data?.elderlyVaccination1 ?: 0}
                                                Lansia Vaksinasi 2         :   ${cakupanVaksinasi.data?.elderlyVaccination2 ?: 0}
                                            """.trimIndent()
                                                )
                                                type = "text/plain"
                                            }, null
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