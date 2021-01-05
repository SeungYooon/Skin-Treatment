package com.example.toyproject.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.toyproject.data.entities.SkinType
import com.example.toyproject.databinding.FragmentCustomDialogBinding
import com.example.toyproject.extensions.GlideApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCustomFragment : Fragment() {

    private val type by lazy { requireArguments().getSerializable(DetailActivity.KEY) as SkinType? }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCustomDialogBinding.bind(view)
        setUp(binding)

    }

    private fun setUp(binding: FragmentCustomDialogBinding) = with(binding) {
        type?.let { binding.txtDescription.setText(it.typeName) }
        GlideApp.with(requireContext())
            .load(type?.skinImg)
            .into(binding.ivDialog)
    }
}