package com.example.toyproject

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.toyproject.databinding.ActivityMainBinding
import com.example.toyproject.ui.skin.SkinActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animation = AnimationUtils.loadAnimation(this, R.anim.item_anims)

        binding.apply {
            txtTitle.startAnimation(animation)
            txtSubTitle.startAnimation(animation)
            lottieView.startAnimation(animation)
            btnStart.startAnimation(animation)
        }

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, SkinActivity::class.java))
        }
    }
}