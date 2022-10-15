package ru.sectorsj.where_to_go.utils.view

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.sectorsj.where_to_go.R

fun BottomNavigationView.setListener(nav: NavController) {
    setOnItemSelectedListener {
        when(it.itemId) {
            R.id.main_page -> {
                nav.navigate(R.id.mainFragment)
                true
            }
            R.id.events_page -> {
                nav.navigate(R.id.topEvents)
                true
            }
            R.id.locations_page -> {
                nav.navigate(R.id.topLocations)
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

fun ImageView.load(link: String) {
    Glide.with(this)
        .load(link)
        .placeholder(R.drawable._28_258)
        .centerCrop()
        .into(this)
}

fun BottomNavigationView.show() {
    visibility = View.VISIBLE
}

fun BottomNavigationView.hide() {
    visibility = View.GONE
}