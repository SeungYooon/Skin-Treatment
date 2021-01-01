package com.example.toyproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.toyproject.data.entities.SkinType
import com.example.toyproject.databinding.FragmentCustomDialogBinding
import com.example.toyproject.extensions.GlideApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCustomFragment : Fragment() {

    private var _binding: FragmentCustomDialogBinding? = null

    private val binding get() = _binding!!

    private val type by lazy { requireArguments().getSerializable(DetailActivity.KEY) as SkinType? }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        type?.let { binding.txtDescription.setText(it.typeName) }
        GlideApp.with(requireContext())
            .load(type?.skinImg)
            .into(binding.ivDialog)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}