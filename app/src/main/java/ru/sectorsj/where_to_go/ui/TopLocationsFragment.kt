package ru.sectorsj.where_to_go.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.databinding.FragmentTopLocationsBinding

class TopLocationsFragment : Fragment() {

    lateinit var binding: FragmentTopLocationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

}