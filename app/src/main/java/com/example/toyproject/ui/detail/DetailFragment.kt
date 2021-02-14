package com.example.toyproject.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.toyproject.R
import com.example.toyproject.base.BaseFragment
import com.example.toyproject.databinding.FragmentDetailBinding
import com.example.toyproject.util.extensions.bindImage
import com.example.toyproject.util.extensions.setAdapter
import com.example.toyproject.viewmodel.SkinViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    lateinit var adapter: DetailAdapter

    private val viewModel: SkinViewModel by viewModels()

    private val safeArgs: DetailFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding =
        FragmentDetailBinding::inflate

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObserver()
        setupType()
    }

    private fun setupUI() {
        binding.apply {
            safeArgs.skinType?.let { it -> txtSkinName.setText(it.typeName) }
            bindImage(ivMainSkin, safeArgs.skinType?.skinImg)
            adapter =
                DetailAdapter { skinInfo ->
                    findNavController().navigate(
                        DetailFragmentDirections.actionDetailFragmentToDetailCustomFragment(
                            skinInfo
                        )
                    )
                }
            setAdapter(rvDetail, adapter)
        }
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.change_bounds)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun setupObserver() {
        viewModel.getSkinsByTitle.observe(viewLifecycleOwner, Observer { skins ->
            adapter.submitList(skins)
        })
    }

    @ExperimentalCoroutinesApi
    private fun setupType() {
        viewModel.setSkinType(safeArgs.skinType?.typeName)
    }
}
