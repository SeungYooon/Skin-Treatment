package com.example.toyproject.ui.skin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.toyproject.R
import com.example.toyproject.base.BaseFragment
import com.example.toyproject.databinding.FragmentSkinBinding
import com.example.toyproject.domain.model.SkinType
import com.example.toyproject.util.extensions.bindAdapterTransform
import com.example.toyproject.util.extensions.setAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SkinFragment : BaseFragment<FragmentSkinBinding>(), SkinAdapter.OnClickListener {

    @Inject
    lateinit var adapter: SkinAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSkinBinding =
        FragmentSkinBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setUpObserver()

        binding.fabFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_skinFragment_to_mySkinFragment)
        }

        binding.fabAllSkin.setOnClickListener {
            findNavController().navigate(R.id.action_skinFragment_to_todaySkinFragment)
        }
    }

    private fun setupUI() {
        binding.apply {
            setAdapter(dsView, adapter)
            bindAdapterTransform(dsView, true)
        }
    }

    private fun setUpObserver() {
        adapter.submitList(
            mutableListOf(
                SkinType.TOXIN,
                SkinType.FILLER,
                SkinType.INJECTION,
                SkinType.LIFTING,
                SkinType.ACNE,
                SkinType.WAXING
            )
        )
    }

    override fun onClick(imageView: ImageView, skinType: SkinType) {
        val extras = FragmentNavigatorExtras(
            imageView to getString(R.string.transition_name)
        )
        val action = SkinFragmentDirections.actionSkinFragmentToDetailFragment(skinType)
        NavHostFragment.findNavController(this@SkinFragment).navigate(action, extras)
    }
}