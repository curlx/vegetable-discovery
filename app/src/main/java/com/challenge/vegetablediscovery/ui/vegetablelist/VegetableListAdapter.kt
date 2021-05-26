package com.challenge.vegetablediscovery.ui.vegetablelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.challenge.vegetablediscovery.databinding.ListItemVegetableBinding
import com.challenge.vegetablediscovery.domain.model.Vegetable

class VegetableListAdapter(
    private val listener: Listener
): ListAdapter<Vegetable, VegetableListViewHolder>(VegetableDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegetableListViewHolder {
        val itemBinding = ListItemVegetableBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VegetableListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: VegetableListViewHolder, position: Int) {
        val vegetable = getItem(position)
        holder.bind(vegetable)
        holder.itemView.setOnClickListener {
            listener.onVegetableItemClick(vegetable.id)
        }
    }

    interface Listener {
        fun onVegetableItemClick(vegetableId: Long)
    }
}