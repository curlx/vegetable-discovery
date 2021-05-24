package com.challenge.vegetablediscovery.ui.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.challenge.vegetablediscovery.databinding.FragmentDiscoveryBinding
import com.challenge.vegetablediscovery.ui.vegetablelist.VegetableListAdapter
import com.challenge.vegetablediscovery.ui.discovery.viewmodel.DiscoveryViewModel
import com.challenge.vegetablediscovery.ui.vegetablelist.VegetableListViewModel

class DiscoveryFragment : Fragment(), VegetableListAdapter.Listener {

    private lateinit var discoveryViewModel: DiscoveryViewModel
    private lateinit var vegetableListViewModel: VegetableListViewModel
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
        vegetableListViewModel = ViewModelProvider(this).get(VegetableListViewModel::class.java)

        _binding = FragmentDiscoveryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVegetableRecyclerView()

        loadVegetableList()
    }

    override fun onVegetableItemClick(vegetableId: Long) {
        val direction = DiscoveryFragmentDirections.actionNavigationDiscoveryToNavigationVegetableDetail(vegetableId)
        findNavController().navigate(direction)
    }

    private fun setupVegetableRecyclerView() {
        binding.vegetableList.run {
            layoutManager = GridLayoutManager(this.context, 1)
            setHasFixedSize(true)
        }
    }

    private fun loadVegetableList() {
        vegetableListViewModel.getVegetableList().observe(viewLifecycleOwner, {
            binding.vegetableList.adapter = VegetableListAdapter(it, this@DiscoveryFragment)
            binding.vegetableList.invalidate()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}