package ru.sectorsj.where_to_go.ui.events

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.event.TopEventAdapter
import ru.sectorsj.where_to_go.databinding.FragmentTopEventsBinding
import ru.sectorsj.where_to_go.utils.view.hideAppBar
import ru.sectorsj.where_to_go.utils.view.showAppBar

class TopEventsFragment : Fragment() {

    lateinit var binding: FragmentTopEventsBinding

    private val viewModel: EventViewModel by viewModels()

    companion object {
        const val EVENT_KEY = "event_key"
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        hideAppBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopEventsBinding.inflate(inflater, container, false)

        val adapter = TopEventAdapter {
            findNavController().navigate(
                R.id.action_topEvents_to_eventDetailsFragment,
                Bundle().apply {
                    putParcelable(EVENT_KEY, it)
                })
            showAppBar()
        }

        binding.listTopEvents.apply {
            setAdapter(adapter)
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    LinearLayoutManager.VERTICAL
                )
            )
            scrollToPosition(0)
        }


        lifecycleScope.launchWhenCreated {
            viewModel.data.collect {
                adapter.submitList(it)
                binding.dashboardSubTitle.text =
                    getString(R.string.dashboard_sub_title, it.size.toString())
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.dataState.collect {
                with(binding) {
                    progressBar.isVisible = it.loading
                    swipeRefresh.isRefreshing = it.refreshing
                }
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshEvents()
        }

        lifecycleScope.launchWhenCreated {
            binding.eventSearchBar.addTextChangedListener(provideTextWatcher(adapter))
        }

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        showAppBar()
    }

    private fun provideTextWatcher(adapter: TopEventAdapter): TextWatcher = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            CoroutineScope(Dispatchers.IO).launch {
                flowOf(s)
                    .debounce(1000).collect { charSeq ->
                    charSeq?.let {
                        viewModel.data.collect { eventList ->
                            eventList.filter { event ->
                                event.title.startsWith(charSeq, true)
                            }.apply {
                                adapter.submitList(this)
                            }
                        }
                    }
                }
            }
        }
        override fun afterTextChanged(s: Editable?) {}
    }
}