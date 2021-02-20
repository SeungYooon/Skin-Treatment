package com.example.toyproject.ui.skin

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.toyproject.R
import com.example.toyproject.base.BaseActivity
import com.example.toyproject.databinding.ActivitySkinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkinActivity : BaseActivity<ActivitySkinBinding>(ActivitySkinBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.navigate(R.id.action_global_skinFragment)
    }
}