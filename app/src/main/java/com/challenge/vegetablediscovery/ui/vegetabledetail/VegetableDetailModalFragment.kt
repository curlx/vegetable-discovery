package com.challenge.vegetablediscovery.ui.vegetabledetail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.challenge.vegetablediscovery.R
import com.challenge.vegetablediscovery.databinding.BottomSheetVegetableDetailBinding
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.glide.GlideApp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class VegetableDetailModalFragment: BottomSheetDialogFragment() {

    private val args: VegetableDetailModalFragmentArgs by navArgs()

    private var _binding: BottomSheetVegetableDetailBinding? = null
    private val binding get() = _binding!!

    private val vegetableDetailViewModel: VegetableDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = BottomSheetVegetableDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vegetableDetailViewModel.getVegetableDetail(args.vegetableId).observe(viewLifecycleOwner, {
            it?.let {
                showVegetableDetail(it)
            } ?: showNotFound()
        })

        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }

    private fun showVegetableDetail(vegetable: Vegetable) {
        binding.name.text = vegetable.name
        GlideApp.with(requireContext())
            .load(vegetable.imageUrl)
            .transform(CenterCrop())
            .placeholder(R.drawable.vegetable_placeholder)
            .into(binding.image)
        binding.description.text = vegetable.description
    }

    private fun showNotFound() {
        // TODO: show not found
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            setOnShowListener {
                (it as? BottomSheetDialog)?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)?.let { parentLayout ->
                    setupFullHeight(parentLayout)
                    BottomSheetBehavior.from(parentLayout).apply {
                        state = BottomSheetBehavior.STATE_EXPANDED
                        skipCollapsed = true
                    }
                }
            }
        }
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}