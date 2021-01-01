package com.example.toyproject.ui.detail

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.toyproject.R
import com.example.toyproject.base.BaseActivity
import com.example.toyproject.data.entities.SkinType
import com.example.toyproject.databinding.ActivityDetailBinding
import com.example.toyproject.viewmodel.ChannelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity :
    BaseActivity<ActivityDetailBinding, ChannelViewModel>({ ActivityDetailBinding.inflate(it) }) {

    override val viewModel: ChannelViewModel by viewModels()

    private val type by lazy { intent.getSerializableExtra(KEY) as SkinType? }

    private lateinit var navController: NavController

    override fun setupUI() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val bundle = bundleOf(KEY to type)
        navController.navigate(R.id.action_global_detailFragment, bundle)
    }

    companion object {
        const val KEY = "skin"

        fun startActivityWithTransition(
            activity: Activity,
            imageView: ImageView,
            type: SkinType
        ) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(KEY, type)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, imageView, imageView.transitionName
            )
            activity.startActivity(intent, options.toBundle())
        }
    }
}