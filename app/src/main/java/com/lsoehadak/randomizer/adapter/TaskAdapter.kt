package com.lsoehadak.randomizer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lsoehadak.randomizer.R
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(
    private val context: Context,
    private var items: ArrayList<String>
) : RecyclerView.Adapter<TaskAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: String) {
            itemView.tv_task.text = "$task"
        }
    }
}