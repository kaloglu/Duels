package com.kaloglu.duels.mobileui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter<M, VH : ItemAdapter.ItemViewHolder<M>> : RecyclerView.Adapter<VH>() {

    var onClickItem: ((M) -> Unit) = {}

    var values = emptyList<M>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            TODO("should be add ViewHolder unless once")

    override fun getItemCount() = values.size

    override fun onBindViewHolder(holder: VH, position: Int) =
            holder.bind(values[position], onClickItem)

    abstract class ItemViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bind(item: M)

        internal fun bind(item: M, onClick: (M) -> Unit) {
            with(itemView) {
                tag = item
                setOnClickListener { onClick(item) }
            }

            bind(item)

        }

    }

}

