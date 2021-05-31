package com.aplikasikaryaanakbangkit.sentiment.team

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.viewmodel.ViewModelFactory
import com.aplikasikaryaanakbangkit.sentiment.core.vo.Status
import com.aplikasikaryaanakbangkit.sentiment.databinding.FragmentTeamBinding
import kotlinx.android.synthetic.main.fragment_home.*

class TeamFragment : Fragment() {

    private var _fragmentTeamBinding: FragmentTeamBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val _binding get() = _fragmentTeamBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _fragmentTeamBinding =
            FragmentTeamBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(requireContext())
        val teamViewModel = ViewModelProvider(this, factory)[TeamViewModel::class.java]

        loadTeams(teamViewModel)

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

                    loadTeams(teamViewModel)
                }, 750)
                onItemLoad()
            }

            fun onItemLoad() {
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun loadTeams(teamViewModel: TeamViewModel) {
        teamViewModel.getDataTeams.observe(viewLifecycleOwner, { teams ->
            if (teams != null) {
                when (teams.status) {
                    Status.LOADING -> {
                        true.shimmerLoading()
                    }
                    Status.SUCCESS -> {
                        _fragmentTeamBinding?.layoutRvDevelopersName?.let {
                            with(it.rvDeveloperName) {
                                val layoutManagerVertical = GridLayoutManager(context, 2)
                                this.layoutManager = layoutManagerVertical
                                this.setHasFixedSize(false)

                                val teamAdapter = TeamAdapter()
                                this.adapter = teamAdapter
                                teamAdapter.submitList(teams.data)
                            }
                        }
                        false.shimmerLoading()
                    }
                    Status.ERROR -> {
                        false.shimmerLoading()
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTeamBinding = null
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