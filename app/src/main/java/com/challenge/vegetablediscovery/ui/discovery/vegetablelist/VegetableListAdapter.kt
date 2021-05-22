package com.challenge.vegetablediscovery.ui.discovery.vegetablelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.vegetablediscovery.databinding.ListItemVegetableBinding
import com.challenge.vegetablediscovery.domain.Vegetable

class VegetableListAdapter(
    private val vegetableList: List<Vegetable>
): RecyclerView.Adapter<VegetableListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegetableListViewHolder {
        val itemBinding = ListItemVegetableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VegetableListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: VegetableListViewHolder, position: Int) {
        vegetableList[position].run(holder::bind)
    }

    override fun getItemCount(): Int = vegetableList.size
}