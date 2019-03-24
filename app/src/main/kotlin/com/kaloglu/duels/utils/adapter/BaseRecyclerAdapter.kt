package com.kaloglu.duels.utils.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaloglu.duels.domain.model.base.BaseModel
import kotlin.properties.Delegates

abstract class BaseRecyclerAdapter<M : BaseModel, VH : BaseViewHolder<M>>
    : RecyclerView.Adapter<VH>(), DiffAdapter {

    var items: List<M> by Delegates.observable(emptyList()) { _, old, new ->
        notifyDiff(old, new) { o, n -> o.id == n.id }
    }
    var onItemClick: ((M) -> Unit) = {}
    var onViewClick: ((M, View) -> Unit) = { _, _ -> }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) =
            holder
                    .setOnViewClick(onViewClick)
                    .bindItem(items[position], onItemClick)

    override fun getItemCount() = items.size

}
