package com.lsoehadak.randomizer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lsoehadak.randomizer.R
import com.lsoehadak.randomizer.model.AppMenu
import kotlinx.android.synthetic.main.item_app_menu.view.*

class AppMenuAdapter(
    private val context: Context,
    private var items: ArrayList<AppMenu>,
    private val listener: AppMenuListener
) : RecyclerView.Adapter<AppMenuAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_app_menu, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface AppMenuListener {
        fun onAppMenuSelected(appMenu: AppMenu)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(appMenu: AppMenu) {
            when (appMenu.type) {
                AppMenu.CREATE_GROUP -> {
                    itemView.container.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.main_theme
                        )
                    )
                }
                AppMenu.TASK_MAPPING -> {
                    itemView.container.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            android.R.color.holo_blue_dark
                        )
                    )
                }
            }

            itemView.iv_icon.setImageResource(appMenu.iconResId)
            itemView.tv_name.text = appMenu.name
            itemView.tv_desc.text = appMenu.desc

            itemView.setOnClickListener {
                listener.onAppMenuSelected(appMenu)
            }
        }
    }
}