package com.example.toyproject.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    @Inject
    lateinit var adapter: DetailAdapter

    private val viewModel: SkinViewModel by viewModels()

    private val safeArgs: DetailFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding =
        FragmentDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setUpObserver()
        setupType()
    }

    private fun setupUI() {
        binding.apply {
            safeArgs.skinType?.let { it -> txtSkinName.setText(it.typeName) }
            bindImage(ivMainSkin, safeArgs.skinType?.skinImg)
            setAdapter(rvDetail, adapter)
        }
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.change_bounds)
    }

    private fun setUpObserver() {
        viewModel.getSkinsByTitle.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupType() {
        when (safeArgs.skinType?.typeName) {
            R.string.toxin -> {
                viewModel.queryChannel.offer("toxin")
            }
            R.string.filler -> {
                viewModel.queryChannel.offer("filler")
            }
            R.string.injection -> {
                viewModel.queryChannel.offer("inject")
            }
            R.string.lifting -> {
                viewModel.queryChannel.offer("lifting")
            }
            R.string.acne -> {
                viewModel.queryChannel.offer("acne")
            }
            R.string.waxing -> {
                viewModel.queryChannel.offer("waxing")
            }
        }
    }
}
