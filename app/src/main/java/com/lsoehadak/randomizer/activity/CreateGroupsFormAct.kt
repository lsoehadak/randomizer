package com.lsoehadak.randomizer.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.lsoehadak.randomizer.R
import kotlinx.android.synthetic.main.activity_create_groups_form.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class CreateGroupsFormAct : AppCompatActivity() {
    private val members = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_groups_form)

        tb.title = "Buat Grup"
        setSupportActionBar(tb)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        et_member_name.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (et_member_name.text.toString().isNotBlank()) {
                    addMember(et_member_name.text.toString())
                    et_member_name.setText("")
                }
                return@setOnEditorActionListener true
            }
            false
        }

        et_number_of_members.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                validateForm()
            }

        })

        // set fab isenabled = false as default state
        fab_next.isEnabled = false

        fab_next.setOnClickListener {
            startActivity(Intent(this, GroupResultAct::class.java).apply {
                putExtra("number_of_member", et_number_of_members.text.toString().toInt())
                putExtra("members", members)
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

    private fun addMember(memberName: String) {
        members.add(memberName)
        val chip = layoutInflater.inflate(
            R.layout.item_chip_entry,
            cg_member_name,
            false
        ) as Chip
        chip.text = memberName
        cg_member_name.addView(chip as View)
        chip.setOnCloseIconClickListener {
            cg_member_name.removeView(chip as View)
            members.remove(chip.text)
            validateForm()
        }

        validateForm()
    }

    private fun validateForm() {
        when {
            et_number_of_members.text.toString().isEmpty() -> {
                fab_next.isEnabled = false
            }
            members.isEmpty() -> {
                fab_next.isEnabled = false
            }
            members.size < et_number_of_members.text.toString().toInt() -> {
                fab_next.isEnabled = false
            }
            else -> {
                fab_next.isEnabled = true
            }
        }
    }
}