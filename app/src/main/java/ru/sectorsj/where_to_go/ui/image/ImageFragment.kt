package ru.sectorsj.where_to_go.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.sectorsj.where_to_go.databinding.FragmentImageBinding
import ru.sectorsj.where_to_go.utils.view.load

const val IMAGE_KEY = "image"

@AndroidEntryPoint
class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf {
            it.containsKey(IMAGE_KEY)
        }?.apply {
            getString(IMAGE_KEY)?.let { imageLink ->
                binding.image.load(imageLink)
            }

        }
    }

}