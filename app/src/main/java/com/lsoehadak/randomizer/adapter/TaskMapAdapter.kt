package com.lsoehadak.randomizer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.lsoehadak.randomizer.R
import com.lsoehadak.randomizer.model.TaskMap
import kotlinx.android.synthetic.main.item_task_map.view.*

class TaskMapAdapter(
    private val context: Context,
    private var items: ArrayList<TaskMap>
) : RecyclerView.Adapter<TaskMapAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_task_map, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(taskMap: TaskMap) {
            itemView.tv_pic.text = "${taskMap.pic}"

            val taskAdapter = TaskAdapter(context, taskMap.tasks)
            itemView.rv_task.adapter = taskAdapter
            itemView.rv_task.layoutManager = LinearLayoutManager(context)
        }
    }
}