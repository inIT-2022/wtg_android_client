package ru.sectorsj.where_to_go.ui.events

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.filter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.DefaultLoadStateAdapter
import ru.sectorsj.where_to_go.adapter.event.TopEventAdapter
import ru.sectorsj.where_to_go.databinding.FragmentTopEventsBinding
import ru.sectorsj.where_to_go.ui.main.MainFragment.Companion.EVENT_KEY
import ru.sectorsj.where_to_go.utils.view.hideAppBar
import ru.sectorsj.where_to_go.utils.view.showAppBar

@AndroidEntryPoint
@OptIn(FlowPreview::class)
class TopEventsFragment : Fragment() {

    lateinit var binding: FragmentTopEventsBinding

    private val viewModel: EventViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hideAppBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopEventsBinding.inflate(inflater, container, false)

        val adapter = prepareAdapter()

        binding.listTopEvents.apply {
            this.adapter = adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.eventsFlow.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest {
                binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
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

    private fun provideTextWatcher(adapter: TopEventAdapter): TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            CoroutineScope(Dispatchers.IO).launch {
                flowOf(s)
                    .debounce(500).collect { charSeq ->
                        charSeq?.let {
                            viewModel.eventsFlow.collectLatest { eventList ->
                                eventList.filter { event ->
                                    event.title.contains(charSeq, true)
                                }.apply {
                                    adapter.submitData(this)
                                }
                            }
                        }
                    }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun prepareAdapter(): TopEventAdapter {
        val adapter = TopEventAdapter { event ->
            findNavController().navigate(
                R.id.action_topEvents_to_eventDetailsFragment,
                Bundle().apply {
                    putParcelable(EVENT_KEY, event)
                }
            )
            showAppBar()
        }
        val stateAdapter = DefaultLoadStateAdapter {
            adapter.refresh()
        }
        adapter.withLoadStateFooter(stateAdapter)
        return adapter
    }
}