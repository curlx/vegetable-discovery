package com.challenge.vegetablediscovery.ui.discovery.vegetablelist

import android.animation.ObjectAnimator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.transition.ViewPropertyTransition
import com.challenge.vegetablediscovery.R
import com.challenge.vegetablediscovery.databinding.ListItemVegetableBinding
import com.challenge.vegetablediscovery.domain.Vegetable
import com.challenge.vegetablediscovery.glide.GlideApp

class VegetableListViewHolder(
    private val itemBinding: ListItemVegetableBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val fadeInAnimator by lazy {
        ViewPropertyTransition.Animator { view ->
            view.alpha = 0f
            val fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
            fadeAnim.duration = 500L
            fadeAnim.start()
        }
    }

    fun bind(vegetable: Vegetable) {
        itemBinding.name.text = vegetable.name
        GlideApp.with(itemBinding.root.context)
            .load(vegetable.imageUrl)
            .transition(GenericTransitionOptions.with(fadeInAnimator))
            .transform(CenterCrop())
            .placeholder(R.drawable.vegetable_placeholder)
            .into(itemBinding.image)
    }
}