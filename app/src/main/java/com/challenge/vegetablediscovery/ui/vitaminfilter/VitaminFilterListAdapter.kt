package com.challenge.vegetablediscovery.ui.vitaminfilter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.vegetablediscovery.databinding.ListItemVitaminBinding
import com.challenge.vegetablediscovery.domain.model.Vitamin

class VitaminFilterListAdapter(
    private val vitamins: List<Vitamin>
): RecyclerView.Adapter<VitaminFilterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VitaminFilterViewHolder {
        val itemBinding = ListItemVitaminBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VitaminFilterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: VitaminFilterViewHolder, position: Int) {
        try { vitamins[position] } catch (ignored: Exception) { null }
            ?.also {
                holder.bind(it)
            }
    }

    override fun getItemCount(): Int = vitamins.size
}