package com.kaloglu.duels.utils.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var onViewClick: ((M, View) -> Unit) = { _, _ -> }

    abstract fun bindItem(item: M)

    internal fun bindItem(item: M, onItemClick: (M) -> Unit) {

        with(itemView) {
            setOnClickListener { onItemClick(item) }
            bindItem(item)
        }
    }

    internal fun setOnViewClick(onViewClick: ((M, View) -> Unit)): BaseViewHolder<M> {
        this.onViewClick = onViewClick
        return this
    }
}