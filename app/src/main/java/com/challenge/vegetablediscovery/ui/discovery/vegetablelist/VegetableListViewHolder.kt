package com.challenge.vegetablediscovery.ui.discovery.vegetablelist

import androidx.recyclerview.widget.RecyclerView
import com.challenge.vegetablediscovery.R
import com.challenge.vegetablediscovery.databinding.ListItemVegetableBinding
import com.challenge.vegetablediscovery.domain.Vegetable

class VegetableListViewHolder(
    private val itemBinding: ListItemVegetableBinding
): RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(vegetable: Vegetable) {
        itemBinding.name.text = vegetable.name
        itemBinding.image.setImageResource(R.drawable.vegetable_placeholder)
    }

}