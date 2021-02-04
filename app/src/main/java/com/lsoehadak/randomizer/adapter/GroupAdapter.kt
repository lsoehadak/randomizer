package com.lsoehadak.randomizer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.lsoehadak.randomizer.R
import com.lsoehadak.randomizer.model.Group
import kotlinx.android.synthetic.main.item_group.view.*

class GroupAdapter(
    private val context: Context,
    private var items: ArrayList<Group>
) : RecyclerView.Adapter<GroupAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_group, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(group: Group) {
            itemView.tv_group_name.text = group.groupName

            for (member in group.members) {
                val chip = LayoutInflater.from(context).inflate(
                    R.layout.item_chip_regular,
                    itemView.cg_group_member,
                    false
                ) as Chip
                chip.text = member
                itemView.cg_group_member.addView(chip as View)
            }
        }
    }
}