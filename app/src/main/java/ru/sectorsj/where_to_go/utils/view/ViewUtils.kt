package ru.sectorsj.where_to_go.utils.view

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.ui.AppBarController


//BottomNavigationView extensions
fun BottomNavigationView.setListener(nav: NavController, context: Context? = null) {
    setOnItemSelectedListener {
        when(it.itemId) {
            R.id.main_page -> {
                nav.navigate(R.id.action_to_mainFragment)
                (context as AppBarController).showAppBar()
                true
            }
            R.id.events_page -> {
                nav.navigate(R.id.action_to_topEventsFragment)
                true
            }
            R.id.locations_page -> {
                nav.navigate(R.id.action_to_topLocationsFragment)
                (context as AppBarController).showAppBar()
                true
            }
            R.id.routes_page -> {
                Log.i("AAAAAAA", "ROUTES PAGE CLICKED")
                (context as AppBarController).showAppBar()
                true
            }
            R.id.search_page -> {
                Log.i("AAAAAAA", "SEARCH PAGE CLICKED")
                (context as AppBarController).showAppBar()
                true
            }
            else -> false
        }
    }
}

fun BottomNavigationView.show() {
    visibility = View.VISIBLE
}

fun BottomNavigationView.hide() {
    visibility = View.GONE
}

fun Fragment.hideAppBar() {
    (requireActivity() as AppBarController).hideAppBar()
}

fun Fragment.showAppBar() {
    (requireActivity() as AppBarController).showAppBar()
}

//ImageView extensions
fun <T> RequestBuilder<T>.roundCorners(cornerRadius: Int) = apply(RequestOptions().transform(RoundedCorners(cornerRadius)))

fun ImageView.load(link: String) {
    Glide.with(this)
        .load(link)
        .placeholder(R.drawable._28_258)
        .into(this)
}

fun ImageView.loadWithRoundCorners(link: String) {
    Glide.with(this)
        .load(link)
        .roundCorners(this.context.resources.getDimension(R.dimen.image_corner_size).toInt())
        .placeholder(R.drawable._28_258)
        .into(this)
}

//ShapeableImageView extensions
fun ShapeableImageView.load(link: String) {
    Glide.with(this)
        .load(link)
        .placeholder(R.drawable._28_258)
        .into(this)
}

fun ShapeableImageView.loadWithRoundCorners(link: String) {
    Glide.with(this)
        .load(link)
        .roundCorners(this.context.resources.getDimension(R.dimen.image_corner_size).toInt())
        .placeholder(R.drawable._28_258)
        .into(this)
}