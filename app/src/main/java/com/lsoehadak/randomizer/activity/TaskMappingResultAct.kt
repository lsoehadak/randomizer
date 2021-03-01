package com.lsoehadak.randomizer.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lsoehadak.randomizer.R
import com.lsoehadak.randomizer.adapter.TaskMapAdapter
import com.lsoehadak.randomizer.model.TaskMap
import kotlinx.android.synthetic.main.activity_group_result.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlin.random.Random

class TaskMappingResultAct : AppCompatActivity() {
    private var pics = arrayListOf<String>()
    private var tasks = arrayListOf<String>()

    private var taskMaps = arrayListOf<TaskMap>()
    private lateinit var adapter: TaskMapAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_mapping_result)

        tb.title = "Hasil Task Mapping"
        setSupportActionBar(tb)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        pics = intent.getStringArrayListExtra("pics")!!
        tasks = intent.getStringArrayListExtra("tasks")!!

        startTaskMapping()

        adapter = TaskMapAdapter(
            this,
            taskMaps
        )
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startTaskMapping() {
        for (pic in pics) {
            val taskMap = TaskMap(pic, ArrayList())
            taskMaps.add(taskMap)
        }

        while (tasks.size > 0) {
            for (taskMap in taskMaps) {
                val random = Random.nextInt(0, tasks.size)
                taskMap.tasks.add(tasks[random])
                tasks.removeAt(random)

                if (tasks.size <= 0) break
            }
        }
    }
}