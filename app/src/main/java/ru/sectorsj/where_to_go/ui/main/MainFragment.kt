package ru.sectorsj.where_to_go.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.event.EventAdapter
import ru.sectorsj.where_to_go.adapter.location.LocationAdapter
import ru.sectorsj.where_to_go.databinding.FragmentMainBinding
import ru.sectorsj.where_to_go.ui.AppBarController
import ru.sectorsj.where_to_go.ui.BottomNavController
import ru.sectorsj.where_to_go.ui.auth.AuthViewModel
import ru.sectorsj.where_to_go.ui.events.EventViewModel
import ru.sectorsj.where_to_go.ui.locations.LocationViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    private val eventViewModel: EventViewModel by viewModels()
    private val locationViewModel: LocationViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()

    companion object {
        const val EVENT_KEY = "event_key"
        const val LOCATION_KEY = "location_key"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val eventAdapter = EventAdapter { event ->
            findNavController().navigate(R.id.action_mainFragment_to_eventDetailsFragment, Bundle().apply {
                putParcelable(EVENT_KEY, event)
            })
        }
        val locationAdapter = LocationAdapter { location ->
            findNavController().navigate(R.id.action_mainFragment_to_locationDetailsFragment, Bundle().apply {
                putParcelable(LOCATION_KEY, location)
            })
        }

        binding.registrationNotice.isVisible = !authViewModel.authentificated

        binding.eventsList.adapter = eventAdapter
        binding.locationsList.adapter = locationAdapter

        lifecycleScope.launchWhenCreated {
            eventViewModel.eventsFlow.collectLatest {
                eventAdapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            eventAdapter.loadStateFlow.collectLatest {
                binding.progressBar.isVisible = it.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launchWhenCreated {
            locationViewModel.locationsFlow.collectLatest {
                locationAdapter.submitData(it)
            }
        }

        binding.noticeCloseBtn.setOnClickListener {
            binding.registrationNotice.visibility = View.GONE
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