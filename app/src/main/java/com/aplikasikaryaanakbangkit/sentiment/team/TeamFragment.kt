package com.aplikasikaryaanakbangkit.sentiment.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        _teamViewModel =
            ViewModelProvider(this).get(TeamViewModel::class.java)

        _fragmentTeamBinding = FragmentTeamBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        val textView: TextView = _binding.textDashboard
        _teamViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTeamBinding = null
    }
}