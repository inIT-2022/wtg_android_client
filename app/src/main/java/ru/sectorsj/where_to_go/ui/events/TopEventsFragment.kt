package ru.sectorsj.where_to_go.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.event.TopEventAdapter
import ru.sectorsj.where_to_go.databinding.FragmentTopEventsBinding

class TopEventsFragment : Fragment() {

    lateinit var binding: FragmentTopEventsBinding

    private val viewModel: EventViewModel by viewModels()

    companion object {
        const val EVENT_KEY = "event_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopEventsBinding.inflate(inflater, container, false)
        val adapter = TopEventAdapter() {
            findNavController().navigate(R.id.action_topEvents_to_eventDetailsFragment, Bundle().apply {
                putParcelable(EVENT_KEY, it)
            })
        }
        binding.listTopEvents.adapter = adapter
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
            viewModel.refreshEvents()
        }
        return binding.root
    }

}