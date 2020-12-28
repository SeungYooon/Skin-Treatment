package com.example.toyproject.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import com.example.toyproject.R
import com.example.toyproject.base.BaseActivity
import com.example.toyproject.databinding.ActivityDetailBinding
import com.example.toyproject.data.SkinType
import com.example.toyproject.viewmodel.ChannelViewModel

class DetailActivity :
    BaseActivity<ActivityDetailBinding, ChannelViewModel>({ ActivityDetailBinding.inflate(it) }) {

    override val viewModel: ChannelViewModel by viewModels()

    private val type by lazy { intent.getSerializableExtra(KEY) as SkinType }

    override fun setupUI() {
        supportFragmentManager.beginTransaction().replace(
            R.id.container,
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY, type)
                }
            }).commit()
    }

    companion object {
        private const val KEY = "skin"

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