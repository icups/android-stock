package id.stockbit.app.ui.main

import android.app.Activity
import android.content.Intent
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import id.stockbit.app.R
import id.stockbit.app.base.BaseActivity
import id.stockbit.app.constant.Nav
import id.stockbit.app.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {

    companion object {
        @JvmStatic
        fun start(context: Activity) {
            val starter = Intent(context, MainActivity::class.java)
            context.startActivity(starter)
            context.finishAffinity()
        }
    }

    override fun onViewCreated() {
        binding.run {
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onStart() {
        super.onStart()
        setupBottomNavigation()
    }

    override fun layoutResources(): Int {
        return R.layout.activity_main
    }

    private fun setupBottomNavigation() {
        binding.run {
            NavigationUI.setupWithNavController(bottomNavMain, navController)
        }
    }

}