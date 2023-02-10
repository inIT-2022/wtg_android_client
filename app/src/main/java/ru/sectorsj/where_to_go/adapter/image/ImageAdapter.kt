package ru.sectorsj.where_to_go.adapter.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.sectorsj.where_to_go.ui.image.IMAGE_KEY
import ru.sectorsj.where_to_go.ui.image.ImageFragment

class ImageAdapter(fragment: Fragment, private val images: List<String>): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = images.size


    override fun createFragment(position: Int): Fragment {
        return ImageFragment().apply {
            arguments = Bundle().apply {
                putString(IMAGE_KEY, images[position].trim())
            }
        }
    }
}