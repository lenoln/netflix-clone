package com.lithiumcode.netflixclone.view.login

import android.content.Intent
import androidx.activity.viewModels
import com.lithiumcode.netflixclone.R
import com.lithiumcode.netflixclone.databinding.ActivityFormLoginBinding
import com.lithiumcode.netflixclone.view.movie.Home
import com.lithiumcode.netflixclone.view.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormLogin : BaseActivity<ActivityFormLoginBinding>(), FormLoginViewModel.ILoginViewModel {

    private val mViewModel: FormLoginViewModel by viewModels()
    override fun contentLayoutId(): Int = R.layout.activity_form_login

    override fun initViewBinding() {
        supportActionBar!!.hide()
        mViewModel.setNavigator(this)
        mViewModel.checkUserLogged()
        setListeners()
    }

    private fun setListeners() {
        mViewDataBinding.txtInscrevaSe.setOnClickListener {
            openFormActivity()
        }

        mViewDataBinding.btnEntrar.setOnClickListener {
            var email = mViewDataBinding.txtEmail.text.toString()
            var senha = mViewDataBinding.txtSenha.text.toString()

            mViewModel.checkForm(email, senha)
        }
    }

    private fun IrParaHome() {
        val home = Intent(this, Home::class.java);
        startActivity(home);
        finish();
    }

    override fun openFormActivity() {
        val intent = Intent(this, FormCadastro::class.java);
        startActivity(intent)
    }

    override fun onLoginSuccess(message: String) {
        showToast(message)
        mViewDataBinding.mensagemErro.text = String()
        IrParaHome()
    }

    override fun onError(error: String) {
        showToast(error)
        mViewDataBinding.mensagemErro.setTextColor(getColor(R.color.orange))
        mViewDataBinding.mensagemErro.text = error
    }
}