package com.example.toyproject.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.toyproject.R
import com.example.toyproject.base.BaseFragment
import com.example.toyproject.data.db.entities.SkinInfo
import com.example.toyproject.databinding.FragmentTodaySkinBinding
import com.example.toyproject.util.State
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
        skinViewModel.uiState.asLiveData().observe(viewLifecycleOwner, Observer {skinList->
            when (skinList) {
                is State.Error -> {
                    hideProgress()
                    showErrorText()
                }
                State.Loading -> {
                    showProgress()
                    hideRecyclerview()
                }
                is State.Success -> {
                    hideProgress()
                    showRecyclerView()
                    todayRandomSkin(skinList.data)
                    adapter.submitList(skinList.data)
                }
            }
        })
    }

    private fun todayRandomSkin(skins: List<SkinInfo>) = skins.random().run {
        binding.apply {
            txtSkinTitle.text = skinTitle
            txtSkinKinds.text = skinKinds
            bindImage(imgTodaySkin, imageUrl)
        }
    }

    private fun showRecyclerView() {
        binding.dsView.isVisible = true
    }

    private fun hideRecyclerview() {
        binding.dsView.isVisible = false
    }

    private fun showProgress() {
        binding.loadingView.isVisible = true
    }

    private fun hideProgress() {
        binding.loadingView.isVisible = false
    }

    private fun showErrorText() {
        binding.txtError.isVisible = true
    }

    override fun onClickSave(skinInfo: SkinInfo) {
        val alert = AlertView(getString(R.string.save_one), "", AlertStyle.IOS)
        alert.addAction(AlertAction(getString(R.string.yes), AlertActionStyle.POSITIVE) {
            skinViewModel.saveSkins(skinInfo)
        })
        alert.show(requireActivity() as AppCompatActivity)
    }

    override fun onClickItem(skinInfo: SkinInfo) {
        findNavController().navigate(
            TodaySkinFragmentDirections.actionTodaySkinFragmentToDetailCustomFragment(
                skinInfo
            )
        )
    }
}