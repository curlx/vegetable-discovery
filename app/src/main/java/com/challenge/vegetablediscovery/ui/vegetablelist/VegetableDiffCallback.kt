package com.challenge.vegetablediscovery.ui.vegetablelist

import androidx.recyclerview.widget.DiffUtil
import com.challenge.vegetablediscovery.domain.model.Vegetable

class VegetableDiffCallback : DiffUtil.ItemCallback<Vegetable>() {

    override fun areItemsTheSame(oldItem: Vegetable, newItem: Vegetable): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vegetable, newItem: Vegetable): Boolean {
        return oldItem == newItem
    }
}