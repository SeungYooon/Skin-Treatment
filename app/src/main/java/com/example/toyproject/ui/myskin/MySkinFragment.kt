package com.example.toyproject.ui.myskin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.toyproject.base.BaseFragment
import com.example.toyproject.databinding.FragmentMySkinBinding
import com.example.toyproject.util.Resource
import com.example.toyproject.util.extensions.setAdapter
import com.example.toyproject.viewmodel.MySkinViewModel
import com.google.android.material.chip.Chip
import com.irozon.alertview.AlertActionStyle
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import com.irozon.alertview.objects.AlertAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MySkinFragment : BaseFragment<FragmentMySkinBinding>(), MySkinAdapter.OnClickListener {

    @Inject
    lateinit var adapter: MySkinAdapter

    private val viewModel: MySkinViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMySkinBinding =
        FragmentMySkinBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupItems()
        setupDeleteItem()
        setupSearch()
        setUpChips()
    }

    private fun setupUI() {
        setAdapter(binding.rvMySkin, adapter)
        binding.searchView.setQuery("", false)
    }

    private fun setupDeleteItem() {
        binding.imgDeleteAll.setOnClickListener {
            val alert = AlertView("Delete Item All", "choice", AlertStyle.IOS)
            alert.addAction(AlertAction("Delete All", AlertActionStyle.NEGATIVE) {
                viewModel.deleteAllSkins()
            })
            alert.show(requireActivity() as AppCompatActivity)
        }
    }

    private fun setupItems() {
        viewModel.skinLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    showProgress(false)
                    showRecyclerView(true)
                    adapter.submitList(it.data)
                }
                is Resource.Error -> {
                    showRecyclerView(false)
                    binding.txtNoItem.isVisible = true
                }
                Resource.Loading -> {
                    showProgress(true)
                    showRecyclerView(false)
                }
            }
        })
        viewModel.loadAllMySkin()
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.queryChannel.offer(it) }
                return true
            }
        })

        viewModel.searchResult.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setUpChips() {
        with(binding) {
            chipToxin.onClick("toxin")
            chipAcne.onClick("acne")
            chipFiller.onClick("filler")
            chipLifting.onClick("lifting")
            chipWaxing.onClick("waxing")
            chipInjection.onClick("inject")
        }
    }

    private fun Chip.onClick(skinTitle: String) {
        setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.fetchBySkinTitle(skinTitle)
            } else viewModel.loadAllMySkin()
        }
    }

    private fun showRecyclerView(status: Boolean) {
        binding.rvMySkin.isVisible = status
    }

    private fun showProgress(status: Boolean) {
        binding.loadingView.isVisible = status
    }

    override fun onClick(skinKinds: String) {
        val alert = AlertView("Item Delete One", "choice", AlertStyle.IOS)
        alert.addAction(AlertAction("Delete", AlertActionStyle.NEGATIVE) {
            viewModel.deleteOneSkin(skinKinds)
        })
        alert.show(requireActivity() as AppCompatActivity)
    }
}