package ru.sectorsj.where_to_go.utils.view

import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.setMarginsBetweenItems(pageMarginPx: Int, offsetPx: Int) {
    clipToPadding = false
    clipChildren = false

    setPageTransformer { page, position ->

        val offset = position * -(2 * offsetPx + pageMarginPx)
        if (this.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                page.translationX = -offset
            } else {
                page.translationX = offset
            }
        } else {
            page.translationY = offset
        }
    }
}

