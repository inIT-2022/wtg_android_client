package ru.sectorsj.where_to_go.ui

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity(), AppBarController {
    override fun hideAppBar() {
        supportActionBar?.hide()
    }

    override fun showAppBar() {
        supportActionBar?.show()
    }
}