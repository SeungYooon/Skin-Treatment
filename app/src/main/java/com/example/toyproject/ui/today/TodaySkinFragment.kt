package com.example.toyproject.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.toyproject.base.BaseFragment
import com.example.toyproject.data.db.entities.Skins
import com.example.toyproject.databinding.FragmentTodaySkinBinding
import com.example.toyproject.util.Resource
import com.example.toyproject.util.extensions.bindAdapterTransform
import com.example.toyproject.util.extensions.bindImage
import com.example.toyproject.util.extensions.setAdapter
import com.example.toyproject.viewmodel.SkinViewModel
import com.irozon.alertview.AlertActionStyle
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import com.irozon.alertview.objects.AlertAction
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodaySkinFragment : BaseFragment<FragmentTodaySkinBinding>(),
    TodaySkinAdapter.OnClickListener {

    @Inject
    lateinit var adapter: TodaySkinAdapter

    private val skinViewModel: SkinViewModel by viewModels()

    lateinit var skinTitle: String

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTodaySkinBinding =
        FragmentTodaySkinBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setUpObserver()
    }

    private fun setupUI() {
        binding.apply {
            setAdapter(dsView, adapter)
            bindAdapterTransform(dsView, true)
        }
    }

    private fun setUpObserver() {
        skinViewModel.getAllSkins.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    showProgress(true)
                    showRecyclerView(false)
                }
                is Resource.Success -> {
                    showProgress(false)
                    showRecyclerView(true)
                    todayRandomSkin(it.data)
                    adapter.submitList(it.data)
                }
                is Resource.Error -> {
                    showProgress(false)
                    binding.txtError.isVisible = true
                }
            }
        })
    }

    private fun todayRandomSkin(skins: List<Skins>) {
        skins.random().apply {
            binding.apply {
                txtSkinTitle.text = skinTitle
                txtSkinKinds.text = skinKinds
                bindImage(imgTodaySkin, imageUrl)
            }
        }
    }

    private fun showRecyclerView(status: Boolean) {
        binding.dsView.isVisible = status
    }

    private fun showProgress(status: Boolean) {
        binding.loadingView.isVisible = status
    }

    override fun onClick(skins: Skins) {
        val alert = AlertView("Item Save", "choice", AlertStyle.IOS)
        alert.addAction(AlertAction("Save", AlertActionStyle.POSITIVE) {
            skinViewModel.saveSkins(skins)
        })
        alert.show(requireActivity() as AppCompatActivity)
    }
}