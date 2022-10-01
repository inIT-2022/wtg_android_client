package ru.sectorsj.where_to_go.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.sectorsj.where_to_go.adapter.event.TopEventAdapter
import ru.sectorsj.where_to_go.databinding.FragmentTopEventsBinding

class TopEventsFragment : Fragment() {

    lateinit var binding: FragmentTopEventsBinding

    private val viewModel: EventViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopEventsBinding.inflate(inflater, container, false)
        val adapter = TopEventAdapter()
        binding.listTopEvents.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return binding.root
    }

}