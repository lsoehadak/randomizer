package com.lsoehadak.randomizer.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.lsoehadak.randomizer.R
import com.lsoehadak.randomizer.adapter.AppMenuAdapter
import com.lsoehadak.randomizer.model.AppMenu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuList = arrayListOf<AppMenu>()
        val createRandomMenuApp = AppMenu(
            AppMenu.CREATE_GROUP,
            R.drawable.ic_baseline_groups_24,
            "Buat Grup",
            "Buat grup dengan membagi anggota secara random"
        )
        val taskMappingMenuApp = AppMenu(
            AppMenu.TASK_MAPPING,
            R.drawable.ic_outline_add_task_24,
            "Task Mapping",
            "Bagi tugas ke semua PIC secara random"
        )

        menuList.add(createRandomMenuApp)
        menuList.add(taskMappingMenuApp)

        val adapter = AppMenuAdapter(
            this,
            menuList,
            object : AppMenuAdapter.AppMenuListener {
                override fun onAppMenuSelected(appMenu: AppMenu) {
                    when (appMenu.type) {
                        AppMenu.CREATE_GROUP -> {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    CreateGroupsFormAct::class.java
                                )
                            )
                        }
                        AppMenu.TASK_MAPPING -> {
                            startActivity(Intent(this@MainActivity, TaskMappingFormAct::class.java))
                        }
                    }
                }
            }
        )

        rv_menu.adapter = adapter
        rv_menu.layoutManager = GridLayoutManager(this, 2)
    }
}