package com.lithiumcode.netflixclone.view

import android.os.Handler
import android.os.Looper
import com.lithiumcode.netflixclone.view.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel<MainViewModel.IMainViewModel>() {
    interface IMainViewModel{
        fun openFormActivity()
    }
    fun timeToOpenLoginScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigator?.openFormActivity()
        }, 2000)
    }
}