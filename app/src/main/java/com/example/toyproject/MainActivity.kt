package com.example.toyproject

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.toyproject.base.BaseActivity
import com.example.toyproject.databinding.ActivityMainBinding
import com.example.toyproject.ui.skin.SkinActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, SkinActivity::class.java))
        }
    }

    private fun setupUI() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.item_anims)
        binding.container.startAnimation(animation)
    }
}