package com.challenge.vegetablediscovery.ui.vitaminfilter

import androidx.recyclerview.widget.RecyclerView
import com.challenge.vegetablediscovery.databinding.ListItemVitaminBinding
import com.challenge.vegetablediscovery.domain.model.Vitamin
import com.challenge.vegetablediscovery.extension.toBackgrounColor
import com.challenge.vegetablediscovery.extension.toNameAndSubGroupName

class VitaminFilterViewHolder(
    private val itemBinding: ListItemVitaminBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(vitamin: Vitamin) {
        val (name, subGroupName) = vitamin.toNameAndSubGroupName()
        itemBinding.vitamin.setVitamin(name, subGroupName, vitamin.toBackgrounColor())
    }
}