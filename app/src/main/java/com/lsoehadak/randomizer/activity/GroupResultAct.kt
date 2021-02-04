package com.lsoehadak.randomizer.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lsoehadak.randomizer.R
import com.lsoehadak.randomizer.adapter.GroupAdapter
import com.lsoehadak.randomizer.model.Group
import kotlinx.android.synthetic.main.activity_group_result.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlin.random.Random

class GroupResultAct : AppCompatActivity() {
    private var numberOfMember: Int = 0
    private var members = arrayListOf<String>()

    private var groups = arrayListOf<Group>()
    private lateinit var adapter: GroupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_result)

        tb.title = "Hasil Pembagian Grup"
        setSupportActionBar(tb)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        numberOfMember = intent.getIntExtra("number_of_member", 1)
        members = intent.getStringArrayListExtra("members")!!

        randomizeGroup()

        adapter = GroupAdapter(
            this,
            groups
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

    private fun randomizeGroup() {
        var groupCounter = 0;

        while (members.size > 0) {
            groupCounter++
            val groupMembers = arrayListOf<String>()
            for (i in 0 until numberOfMember) {
                if (members.size > 0) {
                    val random = Random.nextInt(0, members.size)
                    groupMembers.add(members[random])
                    members.removeAt(random)
                } else {
                    break
                }
            }

            groups.add(
                Group(
                    "Grup #${groupCounter}",
                    groupMembers
                )
            )
        }
    }
}