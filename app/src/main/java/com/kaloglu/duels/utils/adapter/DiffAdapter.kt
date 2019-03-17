package com.kaloglu.duels.utils.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kaloglu.duels.domain.model.base.BaseModel

interface DiffAdapter {

    fun <M : BaseModel> RecyclerView.Adapter<*>.notifyDiff(old: List<M>, new: List<M>, compare: (M, M) -> Boolean) {
        val diff = DiffUtil.calculateDiff(
                object : DiffUtil.Callback() {
                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                            compare(old[oldItemPosition], new[newItemPosition])

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                            old[oldItemPosition] == new[newItemPosition]

                    override fun getOldListSize() = old.size

                    override fun getNewListSize() = new.size
                }
        )

        diff.dispatchUpdatesTo(this)
    }
}