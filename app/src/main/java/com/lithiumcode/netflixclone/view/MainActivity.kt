package com.lithiumcode.netflixclone.view

import android.content.Intent
import androidx.activity.viewModels
import com.lithiumcode.netflixclone.R
import com.lithiumcode.netflixclone.databinding.ActivityMainBinding
import com.lithiumcode.netflixclone.view.common.BaseActivity
import com.lithiumcode.netflixclone.view.login.FormLogin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    MainViewModel.IMainViewModel {
    override fun contentLayoutId(): Int = R.layout.activity_main

    private val mViewModel: MainViewModel by viewModels()

    override fun initViewBinding() {
        // Ocultar actionbar
        supportActionBar!!.hide()

        mViewModel.setNavigator(this)
        mViewModel.timeToOpenLoginScreen()
    }

    override fun openFormActivity() {
        val formLogin = Intent(this, FormLogin::class.java);
        startActivity(formLogin)
        finish()
    }
}