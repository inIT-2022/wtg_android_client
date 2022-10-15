package ru.sectorsj.where_to_go.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.location.OnLocationClickListener
import ru.sectorsj.where_to_go.adapter.location.TopLocationAdapter
import ru.sectorsj.where_to_go.databinding.FragmentTopLocationsBinding
import ru.sectorsj.where_to_go.dto.Location

class TopLocationsFragment : Fragment() {

    lateinit var binding: FragmentTopLocationsBinding
    private val viewModel: LocationViewModel by viewModels()

    companion object {
        const val LOCATION_KEY = "location_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopLocationsBinding.inflate(inflater, container, false)
        val adapter = TopLocationAdapter(object : OnLocationClickListener {
            override fun onLocationClick(location: Location) {
                findNavController().navigate(
                    R.id.action_topLocations_to_locationDetailsFragment,
                    Bundle().apply {
                        putParcelable(LOCATION_KEY, location)
                    })
            }
        })
        binding.listTopLocations.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.dataState.observe(viewLifecycleOwner) {
            with(binding) {
                progressBar.isVisible = it.loading
                swipeRefresh.isRefreshing = it.refreshing
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshLocations()
        }

        return binding.root
    }

}