package com.kaloglu.duels.utils.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaloglu.duels.data.model.BaseModel

/**
 * Base RecyclerViewAdapter that uses BaseViewHolder for click and long click support.
 */
abstract class BaseRecyclerViewAdapter<M : BaseModel, VH : BaseRecyclerViewAdapter.ViewHolder<M>>
    : RecyclerView.Adapter<VH>() {

    private var items: List<M> = emptyList()

    fun setItems(list: List<M>) {
        items = list
//        handleDiffUtil(items)
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    /**
     *
     * @param holder
     * @param position
     *
     * */
    abstract override fun onBindViewHolder(holder: VH, position: Int)

    override fun getItemCount(): Int {
        return items.size
    }

//    override fun getItemViewType(position: Int): Int {
//        return items[position].layoutId
//    }
//    open fun removeItem(item: M) {
//        val pos = items.indexOf(item)
//        if (pos >= 0) {
//            items.removeAt(pos)
//            notifyItemRemoved(pos)
//        }
//    }
//
//    open fun clear() {
//        items.clear()
////        handleDiffUtil(items)
//    }

    abstract class ViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindItem(item: M)
    }

}