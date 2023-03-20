package ru.sectorsj.where_to_go.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.DefaultLoadStateAdapter
import ru.sectorsj.where_to_go.adapter.location.OnLocationClickListener
import ru.sectorsj.where_to_go.adapter.location.TopLocationAdapter
import ru.sectorsj.where_to_go.databinding.FragmentTopLocationsBinding
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.ui.main.MainFragment.Companion.LOCATION_KEY

@AndroidEntryPoint
class TopLocationsFragment : Fragment() {

    lateinit var binding: FragmentTopLocationsBinding
    private val viewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopLocationsBinding.inflate(inflater, container, false)
        val adapter = prepareAdapter()
        binding.listTopLocations.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.locationsFlow.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest {
                with(binding) {
                    swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                }
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }

        return binding.root
    }

    private fun prepareAdapter(): TopLocationAdapter {
        val adapter = TopLocationAdapter(object : OnLocationClickListener {
            override fun onLocationClick(location: Location) {
                findNavController().navigate(
                    R.id.action_topLocations_to_locationDetailsFragment,
                    Bundle().apply {
                        putParcelable(LOCATION_KEY, location)
                    })
            }
        })
        val stateAdapter = DefaultLoadStateAdapter {
            adapter.refresh()
        }
        adapter.withLoadStateFooter(stateAdapter)

        return adapter
    }
}