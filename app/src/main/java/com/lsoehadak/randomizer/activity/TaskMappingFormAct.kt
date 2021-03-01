package com.lsoehadak.randomizer.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.lsoehadak.randomizer.R
import kotlinx.android.synthetic.main.activity_task_mapping_form.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class TaskMappingFormAct : AppCompatActivity() {
    private val pics = arrayListOf<String>()
    private val tasks = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_mapping_form)

        tb.title = "Task Mapping"
        setSupportActionBar(tb)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        et_pic.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (et_pic.text.toString().isNotBlank()) {
                    addPic(et_pic.text.toString())
                    et_pic.setText("")
                }
                return@setOnEditorActionListener true
            }
            false
        }

        et_task.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (et_task.text.toString().isNotBlank()) {
                    addTask(et_task.text.toString())
                    et_task.setText("")
                }
                return@setOnEditorActionListener true
            }
            false
        }

        // set fab isenabled = false as default state
        fab_next.isEnabled = false

        fab_next.setOnClickListener {
            startActivity(Intent(this, TaskMappingResultAct::class.java).apply {
                putExtra("pics", pics)
                putExtra("tasks", tasks)
            })
        }
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

    private fun addPic(pic: String) {
        pics.add(pic)
        val chip = layoutInflater.inflate(
            R.layout.item_chip_entry,
            cg_pic,
            false
        ) as Chip
        chip.text = pic
        cg_pic.addView(chip as View)
        chip.setOnCloseIconClickListener {
            cg_pic.removeView(chip as View)
            pics.remove(chip.text)
            validateForm()
        }

        if (cg_pic.visibility == View.GONE) cg_pic.visibility = View.VISIBLE

        validateForm()
    }

    private fun addTask(task: String) {
        tasks.add(task)
        val chip = layoutInflater.inflate(
            R.layout.item_chip_entry,
            cg_pic,
            false
        ) as Chip
        chip.text = task
        cg_task.addView(chip as View)
        chip.setOnCloseIconClickListener {
            cg_task.removeView(chip as View)
            tasks.remove(chip.text)
            validateForm()
        }

        validateForm()
    }


    private fun validateForm() {
        when {
            pics.isEmpty() -> {
                fab_next.isEnabled = false
                cg_pic.visibility = View.GONE
            }
            tasks.isEmpty() -> {
                fab_next.isEnabled = false
            }
            tasks.size < pics.size -> {
                fab_next.isEnabled = false
            }
            else -> {
                fab_next.isEnabled = true
            }
        }
    }

}