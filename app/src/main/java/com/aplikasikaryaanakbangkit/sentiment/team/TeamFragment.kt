package com.aplikasikaryaanakbangkit.sentiment.team

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentTeamBinding

class TeamFragment : Fragment() {

    private lateinit var _teamViewModel: TeamViewModel
    private var _fragmentTeamBinding: FragmentTeamBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val _binding get() = _fragmentTeamBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragmentTeamBinding = FragmentTeamBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(requireContext())
        _teamViewModel = ViewModelProvider(this, factory)[TeamViewModel::class.java]

        val teamAdapter = TeamAdapter()

        _teamViewModel.getDataTeams.observe(viewLifecycleOwner, { teams ->
            if (teams != null) {
                when (teams.status) {
                    Status.LOADING -> {
                        true.loading()
                    }
                    Status.SUCCESS -> {
                        false.loading()
                        teamAdapter.submitList(teams.data)
                        Log.d("Team Fragment", teams.data.toString())
                    }
                    Status.ERROR -> {
                        false.loading()
                        _binding.viewError.viewError.visibility = View.VISIBLE
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

        _fragmentTeamBinding?.layoutRvDevelopersName?.let {
            with(it.rvDeveloperName) {
                val layoutManagerVertical = LinearLayoutManager(context)
                this.layoutManager = layoutManagerVertical
                this.setHasFixedSize(false)
                this.adapter = teamAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTeamBinding = null
    }

    private fun Boolean.loading() {
        if (this) {
            _fragmentTeamBinding?.progressBar?.visibility ?: View.VISIBLE
        } else {
            _fragmentTeamBinding?.progressBar?.visibility ?: View.GONE
        }
    }
}