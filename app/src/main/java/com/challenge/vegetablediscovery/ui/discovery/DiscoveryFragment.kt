package com.challenge.vegetablediscovery.ui.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.challenge.vegetablediscovery.R
import com.challenge.vegetablediscovery.databinding.FragmentDiscoveryBinding
import com.challenge.vegetablediscovery.ui.vegetablelist.VegetableListAdapter
import com.challenge.vegetablediscovery.ui.discovery.viewmodel.DiscoveryViewModel
import com.challenge.vegetablediscovery.ui.vegetablelist.VegetableListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoveryFragment : Fragment(), VegetableListAdapter.Listener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var discoveryViewModel: DiscoveryViewModel
    private val vegetableListViewModel: VegetableListViewModel by viewModel<VegetableListViewModel>()
    private var _binding: FragmentDiscoveryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        setupStatusView()
    }

    private fun setupStatusView() {
        vegetableListViewModel.statusMessage.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { message ->
                // TODO: use snackbar
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        })

        binding.swipeContainer.setOnRefreshListener(this)
        vegetableListViewModel.isRefreshing.observe(viewLifecycleOwner, {
            binding.swipeContainer.isRefreshing = it
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
            adapter.submitList(vegetables)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}