package com.aplikasikaryaanakbangkit.sentiment.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.databinding.ItemDeveloperNameBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    private var listTeams = ArrayList<TeamsEntity>()

    fun setTeams(teams: List<TeamsEntity>?) {
        if (teams == null) return
        this.listTeams.clear()
        this.listTeams.addAll(teams)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemDeveloperNameBinding =
            ItemDeveloperNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(itemDeveloperNameBinding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val teams = listTeams[position]
        holder.bind(teams)
    }

    override fun getItemCount(): Int = listTeams.size

    class TeamViewHolder(private val binding: ItemDeveloperNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: TeamsEntity) {
            with(binding) {
                developerNameTv.text = team.name
                Glide.with(itemView.context)
                    .load(team.urlPicture)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(developerImage)
            }
        }
    }

}