package com.example.toyproject.ui.skin

import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.toyproject.base.BaseActivity
import com.example.toyproject.data.entities.Categories
import com.example.toyproject.data.entities.SkinType
import com.example.toyproject.databinding.ActivitySkinBinding
import com.example.toyproject.extensions.bindAdapterTransform
import com.example.toyproject.ui.detail.DetailActivity.Companion.startActivityWithTransition
import com.example.toyproject.utils.Constants.PLUGIN_KEY
import com.example.toyproject.utils.Status
import com.example.toyproject.viewmodel.ChannelViewModel
import com.zoyi.channel.plugin.android.ChannelIO
import com.zoyi.channel.plugin.android.open.config.BootConfig
import com.zoyi.channel.plugin.android.open.enumerate.BootStatus
import com.zoyi.channel.plugin.android.open.enumerate.ChannelButtonPosition
import com.zoyi.channel.plugin.android.open.option.ChannelButtonOption
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SkinActivity :
    BaseActivity<ActivitySkinBinding, ChannelViewModel>({ ActivitySkinBinding.inflate(it) }),
    SkinAdapter.OnClickListener {

    @Inject
    lateinit var adapter: SkinAdapter

    override val viewModel: ChannelViewModel by viewModels()

    private val bootConfig = BootConfig.create(PLUGIN_KEY)
        .setChannelButtonOption(ChannelButtonOption(ChannelButtonPosition.RIGHT, 16f, 16f))

    override fun setupUI() {
        binding.apply {
            dsView.adapter = adapter
            bindAdapterTransform(dsView, true)
        }

        ChannelIO.boot(
            bootConfig
        ) { bootStatus, user ->
            if (bootStatus == BootStatus.SUCCESS && user != null) {
                ChannelIO.showChannelButton()
                binding.fabZoy.setOnClickListener {
                    ChannelIO.showMessenger(this)
                }
            } else {
                ChannelIO.hideChannelButton()
            }
        }

        if (ChannelIO.hasStoredPushNotification(this)) {
            ChannelIO.openStoredPushNotification(this)
        }
    }

    override fun setupAPI() {
        viewModel.getCategory().observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.apply {
                        loadingView.isVisible = true
                        dsView.isVisible = false
                    }
                }
                Status.SUCCESS -> {
                    binding.apply {
                        loadingView.isVisible = false
                        dsView.isVisible = true
                    }
                    it.data?.let { category ->
                        renderList(category)
                    }
                    adapter.submitList(
                        mutableListOf(
                            SkinType.TOXIN,
                            SkinType.FILLER,
                            SkinType.INJECTION,
                            SkinType.LIFTING,
                            SkinType.ACNE,
                            SkinType.PIGMENT,
                            SkinType.SKIN_BOOSTER,
                            SkinType.SKIN_CARE,
                            SkinType.WAXING,
                            SkinType.BODY,
                            SkinType.ANTI_AGING
                        )
                    )
                }
                Status.ERROR -> {
                    Log.v(TAG, it.msg.toString())
                }
            }
        })
        viewModel.fetchCategory(limit = 15)
    }

    private fun renderList(category: Categories) {
        Log.v(TAG, category.toString())
    }

    override fun onClick(imageView: ImageView, skinType: SkinType) {
        startActivityWithTransition(this, imageView, skinType)
    }

    companion object {
        private const val TAG = "SkinActivity"
    }
}