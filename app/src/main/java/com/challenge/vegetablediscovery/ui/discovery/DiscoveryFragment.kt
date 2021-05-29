package com.challenge.vegetablediscovery.ui.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.challenge.vegetablediscovery.R
import com.challenge.vegetablediscovery.databinding.FragmentDiscoveryBinding
import com.challenge.vegetablediscovery.ui.vegetablelist.VegetableListAdapter
import com.challenge.vegetablediscovery.ui.discovery.viewmodel.DiscoveryViewModel
import com.challenge.vegetablediscovery.ui.vegetablelist.VegetableListViewModel
import com.challenge.vegetablediscovery.ui.vitaminfilter.VitaminFilterListAdapter
import com.challenge.vegetablediscovery.ui.vitaminfilter.VitaminFilterViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoveryFragment : Fragment(), VegetableListAdapter.Listener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var discoveryViewModel: DiscoveryViewModel
    private val vegetableListViewModel: VegetableListViewModel by viewModel<VegetableListViewModel>()
    private val vitaminFilterViewModel: VitaminFilterViewModel by viewModel<VitaminFilterViewModel>()
    private var _binding: FragmentDiscoveryBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discoveryViewModel = ViewModelProvider(this).get(DiscoveryViewModel::class.java)

        _binding = FragmentDiscoveryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupVegetableRecyclerView()
        setupVitaminFilterRecyclerView()
        setupStatusView()
    }

    private fun setupStatusView() {
        vegetableListViewModel.issueMessage.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { message ->
                Snackbar.make(binding.constraintLayout, message, Snackbar.LENGTH_INDEFINITE).apply {
                    setAction(R.string.ok) { dismiss() }
                    setMaxInlineActionWidth(80)
                }.show()
            }
        })

        binding.swipeContainer.setOnRefreshListener(this)
        vegetableListViewModel.isRefreshing.observe(viewLifecycleOwner, {
            binding.swipeContainer.isRefreshing = it
        })

        vegetableListViewModel.vegetables.observe(viewLifecycleOwner, { vegetables ->
            binding.emptyVegetableInformation.isVisible = vegetables.isEmpty()
        })
    }

    override fun onRefresh() {
        vegetableListViewModel.refreshVegetableCache()
    }

    override fun onVegetableItemClick(vegetableId: Long) {
        val direction = DiscoveryFragmentDirections.actionNavigationDiscoveryToNavigationVegetableDetail(vegetableId)
        findNavController().navigate(direction)
    }

    private fun setupVegetableRecyclerView() {
        val adapter = VegetableListAdapter(this)

        binding.vegetableList.run {
            layoutManager = GridLayoutManager(this.context, resources.getInteger(R.integer.grid_size))
            setHasFixedSize(true)
            setAdapter(adapter)
        }

        vegetableListViewModel.vegetables.observe(viewLifecycleOwner, { vegetables ->
            binding.vegetableList.isVisible = vegetables.isNotEmpty()
            adapter.submitList(vegetables)
        })
    }

    private fun setupVitaminFilterRecyclerView() {
        val adapter = VitaminFilterListAdapter(vitaminFilterViewModel.vitaminFilters)
        val linearLayoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        binding.vitaminFilter.run {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            setAdapter(adapter)
        }
        // bind a vitamin filter to filter vegetable result
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.vitaminFilter)
        binding.vitaminFilter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    snapHelper.findSnapView(linearLayoutManager)?.let {
                        val position = linearLayoutManager.getPosition(it)
                        if (position != RecyclerView.NO_POSITION) {
                            val snapVitamin = vitaminFilterViewModel.vitaminFilters[position]
                            if (snapVitamin != vegetableListViewModel.selectedFilter.value) {
                                vegetableListViewModel.setSelectedFilter(snapVitamin)
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.vitaminFilter.clearOnScrollListeners()
        _binding = null
    }
}