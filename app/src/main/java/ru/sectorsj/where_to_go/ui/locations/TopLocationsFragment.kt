package ru.sectorsj.where_to_go.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.location.TopLocationAdapter
import ru.sectorsj.where_to_go.databinding.FragmentTopLocationsBinding

class TopLocationsFragment : Fragment() {

    lateinit var binding: FragmentTopLocationsBinding
    private val viewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopLocationsBinding.inflate(inflater, container, false)
        val adapter = TopLocationAdapter()
        binding.listTopLocations.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

}