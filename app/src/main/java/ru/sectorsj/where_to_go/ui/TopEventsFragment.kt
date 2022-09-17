package ru.sectorsj.where_to_go.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sectorsj.where_to_go.databinding.FragmentTopEventsBinding

class TopEventsFragment : Fragment() {

    lateinit var binding: FragmentTopEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

}