package com.example.toyproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.toyproject.base.BaseFragment
import com.example.toyproject.databinding.FragmentDetailCustomBinding
import com.example.toyproject.util.extensions.bindImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCustomFragment : BaseFragment<FragmentDetailCustomBinding>() {
    private val safeArgs: DetailCustomFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailCustomBinding =
        FragmentDetailCustomBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            txtDescription.text = safeArgs.skins?.description
            txtPointDescription.text = safeArgs.skins?.point
            bindImage(imgDetailCustom, safeArgs.skins?.imageUrl)
        }
    }
}