package com.kaloglu.duels.adapter.tournament

import android.view.ViewGroup
import com.kaloglu.duels.R
import com.kaloglu.duels.presentation.interfaces.tournament.Model
import com.kaloglu.duels.utils.adapter.BaseRecyclerAdapter
import com.kaloglu.duels.utils.extensions.inflate
import com.kaloglu.duels.viewholder.tournament.TournamentViewHolder

class TournamentListAdapter : BaseRecyclerAdapter<Model, TournamentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            TournamentViewHolder(parent.inflate(R.layout.tournament_list_item, false))

}

