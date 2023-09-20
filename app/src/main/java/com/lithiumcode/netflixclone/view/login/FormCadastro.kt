package com.lithiumcode.netflixclone.view.login

import androidx.activity.viewModels
import com.lithiumcode.netflixclone.R
import com.lithiumcode.netflixclone.databinding.ActivityFormCadastroBinding
import com.lithiumcode.netflixclone.view.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormCadastro : BaseActivity<ActivityFormCadastroBinding>(),
    CadastroViewModel.ICadastroViewModel {

    private val mViewModel: CadastroViewModel by viewModels()
    override fun contentLayoutId(): Int = R.layout.activity_form_cadastro

    override fun initViewBinding() {
        mViewModel.setNavigator(this)
        supportActionBar!!.hide()
        setupToolbar()
        setListeners()
    }

    private fun setListeners() {
        mViewDataBinding.apply {
            btnCadastrar.setOnClickListener {
                val email = editTextEmail.text.toString()
                val password = editTextSenha.text.toString()

                mViewModel.checkForm(email, password)
            }
        }
    }

    private fun setupToolbar() {
        val toolbar = mViewDataBinding.toolbarCadastro;
        toolbar.setBackgroundColor(getColor(R.color.white));
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo));
    }

    private fun resetForm() {
        mViewDataBinding.apply {
            editTextEmail.setText(String())
            editTextSenha.setText(String())
        }
    }

    override fun onError(error: String) {
        showToast(error)
        mViewDataBinding.mensagemErro.setTextColor(getColor(R.color.red))
        mViewDataBinding.mensagemErro.text = error
    }

    override fun onSaveSuccess(message: String) {
        showToast(message)
        resetForm()
    }
}