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
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.toyproject.R
import com.example.toyproject.base.BaseFragment
import com.example.toyproject.data.db.entities.SkinInfo
import com.example.toyproject.databinding.FragmentMySkinBinding
import com.example.toyproject.util.State
import com.example.toyproject.util.extensions.setAdapter
import com.example.toyproject.viewmodel.MySkinViewModel
import com.irozon.alertview.AlertActionStyle
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import com.irozon.alertview.objects.AlertAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@AndroidEntryPoint
class MySkinFragment : BaseFragment<FragmentMySkinBinding>(), MySkinAdapter.OnClickListener {

    @Inject
    lateinit var adapter: MySkinAdapter

    private val viewModel: MySkinViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMySkinBinding =
        FragmentMySkinBinding::inflate

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        loadSkins()
        setupDeleteAll()
        setupSearch()
        setupChips()
    }

    private fun setupUI() {
        setAdapter(binding.rvMySkin, adapter)
        binding.searchView.setQuery("", false)
    }

    private fun setupDeleteAll() {
        binding.imgDeleteAll.setOnClickListener {
            val alert = AlertView(getString(R.string.delete_all), "", AlertStyle.IOS)
            alert.addAction(AlertAction(getString(R.string.yes), AlertActionStyle.NEGATIVE) {
                viewModel.deleteAllSkins()
            })
            alert.show(requireActivity() as AppCompatActivity)
        }
    }

    private fun loadSkins() {
        viewModel.uiState.asLiveData().observe(viewLifecycleOwner, Observer { skinList ->
            when (skinList) {
                State.Empty -> {
                    showEmptyList()
                }
                is State.Error -> {
                    hideRecyclerView()
                    showErrorText()
                }
                State.Loading -> {
                    showProgress()
                    hideRecyclerView()
                }
                is State.Success -> {
                    hideProgress()
                    hideEmptyList()
                    showRecyclerView()
                    adapter.submitList(skinList.data)
                }
            }
        })
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
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

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun setupChips() {
        viewModel.fetchBySkinTitle.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            viewModel.fetchBySkinTitle(checkedId)
        }
    }

    private fun showRecyclerView() {
        binding.rvMySkin.isVisible = true
    }

    private fun hideRecyclerView() {
        binding.rvMySkin.isVisible = false
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

    private fun showEmptyList() {
        binding.txtNoItem.isVisible = true
    }

    private fun hideEmptyList() {
        binding.txtNoItem.isVisible = false
    }

    override fun onClickDelete(skinKinds: String) {
        val alert = AlertView(getString(R.string.delete_one), "", AlertStyle.IOS)
        alert.addAction(AlertAction(getString(R.string.yes), AlertActionStyle.NEGATIVE) {
            viewModel.deleteOneSkin(skinKinds)
        })
        alert.show(requireActivity() as AppCompatActivity)
    }

    override fun onClickItem(skinInfo: SkinInfo) {
        findNavController().navigate(
            MySkinFragmentDirections.actionMySkinFragmentToDetailCustomFragment(skinInfo)
        )
    }
}