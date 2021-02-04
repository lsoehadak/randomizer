package com.lsoehadak.randomizer.model

data class AppMenu(
    val type: Int,
    val iconResId: Int,
    val name: String,
    val desc: String
) {
    companion object {
        const val CREATE_GROUP = 1
    }
}