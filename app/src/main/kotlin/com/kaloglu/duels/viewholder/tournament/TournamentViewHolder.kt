package com.kaloglu.duels.viewholder.tournament

import android.view.View
import com.kaloglu.duels.presentation.interfaces.tournament.Model
import com.kaloglu.duels.utils.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.tournament_list_item.view.*

class TournamentViewHolder(itemView: View) : BaseViewHolder<Model>(itemView) {
    override fun bindItem(item: Model) {
        itemView.apply {
            tournamentName.text = item.name
            tournamentMore.setOnClickListener { onViewClick.invoke(item, it) }
        }
    }

}