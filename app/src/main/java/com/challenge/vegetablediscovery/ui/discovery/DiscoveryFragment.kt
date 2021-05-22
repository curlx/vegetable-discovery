package com.challenge.vegetablediscovery.ui.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.challenge.vegetablediscovery.databinding.FragmentDiscoveryBinding
import com.challenge.vegetablediscovery.ui.discovery.vegetablelist.VegetableListAdapter
import com.challenge.vegetablediscovery.ui.discovery.viewmodel.DiscoveryViewModel
import com.challenge.vegetablediscovery.ui.discovery.vegetablelist.VegetableListViewModel

class DiscoveryFragment : Fragment() {

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
    }

    private fun setupVegetableRecyclerView() {
        binding.vegetableList.run {
            layoutManager = GridLayoutManager(this.context, 1)

            vegetableListViewModel.vegetables.observe(viewLifecycleOwner, {
                adapter = VegetableListAdapter(it)
                invalidate()
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}