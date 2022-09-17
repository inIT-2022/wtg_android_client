package ru.sectorsj.where_to_go.utils.viewUtil

import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.sectorsj.where_to_go.R

fun BottomNavigationView.setListener() {
    setOnItemSelectedListener {
        when(it.itemId) {
            R.id.main_page -> {
                println("AAAAAA MAIN PAGE")
                true
            }
            R.id.events_page -> {
                Log.i("AAAAAAA", "EVENTS PAGE CLICKED")
                true
            }
            R.id.locations_page -> {
                Log.i("AAAAAAA", "LOCATIONS PAGE CLICKED")
                true
            }
            R.id.routes_page -> {
                Log.i("AAAAAAA", "ROUTES PAGE CLICKED")
                true
            }
            R.id.search_page -> {
                Log.i("AAAAAAA", "SEARCH PAGE CLICKED")
                true
            }
            else -> false
        }
    }
}