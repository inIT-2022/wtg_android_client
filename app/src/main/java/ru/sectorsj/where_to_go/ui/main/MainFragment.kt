package ru.sectorsj.where_to_go.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.event.EventAdapter
import ru.sectorsj.where_to_go.adapter.location.LocationAdapter
import ru.sectorsj.where_to_go.databinding.FragmentMainBinding
import ru.sectorsj.where_to_go.ui.AppBarController
import ru.sectorsj.where_to_go.ui.BottomNavController
import ru.sectorsj.where_to_go.ui.events.EventViewModel
import ru.sectorsj.where_to_go.ui.locations.LocationViewModel

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    private val eventViewModel: EventViewModel by viewModels()
    private val locationViewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val eventAdapter = EventAdapter {
            findNavController().navigate(R.id.action_mainFragment_to_topEvents)
        }
        val locationAdapter = LocationAdapter {
            findNavController().navigate(R.id.actions_mainFragment_to_topLocations)
        }

        binding.eventsList.adapter = eventAdapter
        binding.locationsList.adapter = locationAdapter

        eventViewModel.data.observe(viewLifecycleOwner) {
            eventAdapter.submitList(it)
        }
        locationViewModel.data.observe(viewLifecycleOwner) {
            locationAdapter.submitList(it)
        }
        eventViewModel.dataState.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it.loading
        }

        binding.bikeRoute.setOnClickListener {  }
        binding.walkingRoute.setOnClickListener {  }
        binding.carRoute.setOnClickListener {  }
        binding.restContainer.setOnClickListener {  }
        binding.buttonArrow.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentRest)
        }
        binding.noticeTxt.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_signUpFragment)
            (requireActivity() as AppBarController).hideAppBar()
            (requireActivity() as BottomNavController).hideBottomNav()
        }
        return binding.root
    }

}