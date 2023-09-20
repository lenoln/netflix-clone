package com.lithiumcode.netflixclone.view.common

import android.util.Log
import androidx.lifecycle.ViewModel

open class BaseViewModel<N> : ViewModel() {

    var mNavigator: N? = null
    val navigator: N?
        get() {
            if (mNavigator == null) Log.e(
                "VM_NAVIGATOR_ERROR",
                "É necessário definir uma interface de resposta. Use setNavigator"
            )
            return mNavigator
        }

    fun setNavigator(mNavigator: N) {
        this.mNavigator = mNavigator
    }
}