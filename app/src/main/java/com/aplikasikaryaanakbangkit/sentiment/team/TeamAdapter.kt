package com.aplikasikaryaanakbangkit.sentiment.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aplikasikaryaanakbangkit.sentiment.R
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.teams.TeamsEntity
import com.aplikasikaryaanakbangkit.sentiment.databinding.ItemDeveloperNameBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TeamAdapter :
    PagedListAdapter<TeamsEntity, TeamAdapter.TeamViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TeamsEntity>() {
            override fun areItemsTheSame(
                    oldItem: TeamsEntity,
                    newItem: TeamsEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                    oldItem: TeamsEntity,
                    newItem: TeamsEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemDeveloperNameBinding =
            ItemDeveloperNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(itemDeveloperNameBinding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val teams = getItem(position)
        if (teams != null) {
            holder.bind(teams)
        }
    }

    class TeamViewHolder(private val binding: ItemDeveloperNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: TeamsEntity) {
            with(binding) {
                developerNameTv.text = team.name
                Glide.with(itemView.context)
                    .load(team.urlPicture)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_person)
                    )
                    .into(developerImage)
            }
        }
    }

}