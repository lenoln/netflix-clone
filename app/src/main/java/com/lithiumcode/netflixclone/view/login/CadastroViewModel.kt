package com.lithiumcode.netflixclone.view.login

import android.content.Context
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.lithiumcode.netflixclone.R
import com.lithiumcode.netflixclone.view.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CadastroViewModel @Inject constructor(
    private val context: Context
) : BaseViewModel<CadastroViewModel.ICadastroViewModel>() {

    interface ICadastroViewModel {
        fun onError(error: String)
        fun onSaveSuccess(message: String)
    }

    fun checkForm(email: String, password: String) {
        if (email.isEmpty()) {
            navigator?.onError(context.getString(R.string.email_warning))
        } else if (password.isEmpty()) {
            navigator?.onError(context.getString(R.string.password_warning))
        } else {
            doSave(email, password)
        }
    }

    fun doSave(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    navigator?.onSaveSuccess(context.getString(R.string.save_success))
                }
            }.addOnFailureListener {
                var erro = it;
                var errorMessage:String
                when {
                    erro is FirebaseAuthWeakPasswordException -> errorMessage =
                        context.getString(R.string.password_weak_error)

                    erro is FirebaseAuthUserCollisionException -> errorMessage =
                        context.getString(R.string.alread_user_error)

                    erro is FirebaseNetworkException -> errorMessage =
                        context.getString(R.string.network_error)

                    else -> errorMessage = context.getString(R.string.cadastro_error)
                }
                navigator?.onError(errorMessage)
            }
    }
}