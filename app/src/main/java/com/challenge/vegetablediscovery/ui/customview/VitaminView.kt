package com.challenge.vegetablediscovery.ui.customview

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.text.scale
import com.challenge.vegetablediscovery.databinding.ViewVitaminBinding

class VitaminView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ViewVitaminBinding = ViewVitaminBinding.inflate(LayoutInflater.from(context), this, true)

    fun setVitamin(name: String, subGroupName: String = "", @ColorRes colorRes: Int) {
        binding.text.text = SpannableStringBuilder()
            .append(name)
            .scale(0.7f) { append(subGroupName) }
        binding.background.backgroundTintList = ContextCompat.getColorStateList(context, colorRes)
    }
}