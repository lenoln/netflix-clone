package com.lithiumcode.netflixclone.view.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.lithiumcode.netflixclone.R
import com.lithiumcode.netflixclone.view.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FormLoginViewModel @Inject constructor(
    private val context: Context
): BaseViewModel<FormLoginViewModel.ILoginViewModel>() {
    interface ILoginViewModel{
        fun openFormActivity()
        fun onLoginSuccess(message: String)
        fun onError(error: String)
    }

    private val _currentUser = MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser> get() = _currentUser

    fun checkUserLogged(){
        _currentUser.value = FirebaseAuth.getInstance().currentUser

        if(currentUser == null){
            navigator?.openFormActivity()
        }
    }

    fun doLogin(email: String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    navigator?.onLoginSuccess(context.getString(R.string.login_success))

                }
            }.addOnFailureListener {
                var erro = it
                var errorMessage = String()
                when{
                    erro is FirebaseAuthInvalidCredentialsException -> navigator?.onError(context.getString(R.string.email_or_password_error))
                    erro is FirebaseNetworkException -> navigator?.onError(context.getString(R.string.network_error))
                    else -> navigator?.onError(context.getString(R.string.firebase_generic_error))
                }
                navigator?.onError(errorMessage)
            }
    }

    fun checkForm(email: String, password: String) {
        if(email.isEmpty()) {
            navigator?.onError(context.getString(R.string.email_warning))
        }else if(password.isEmpty()) {
            navigator?.onError(context.getString(R.string.password_warning))
        }else {
            doLogin(email, password)
        }
    }
}