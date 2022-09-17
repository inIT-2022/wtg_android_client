package ru.sectorsj.where_to_go.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.sectorsj.where_to_go.databinding.ActivityAppBinding
import ru.sectorsj.where_to_go.utils.viewUtil.setListener

class AppActivity : AppCompatActivity() {
    lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        val bottomNav = binding.bottomNav
        bottomNav.setListener()
    }
}