package com.lithiumcode.netflixclone.view.movie

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lithiumcode.netflixclone.view.adapter.FilmesAdapter
import com.lithiumcode.netflixclone.Model.Filmes
import com.lithiumcode.netflixclone.R
import com.lithiumcode.netflixclone.databinding.ActivityHomeBinding
import com.lithiumcode.netflixclone.view.common.BaseActivity
import com.lithiumcode.netflixclone.view.login.FormLogin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : BaseActivity<ActivityHomeBinding>(), HomeViewModel.IFilmeListViewModel {

    private val mViewModel: HomeViewModel by viewModels()
    override fun contentLayoutId(): Int = R.layout.activity_home

    override fun initViewBinding() {
        mViewModel.setNavigator(this)
        setListeners()
        mViewModel.getMovies()
    }

    private fun setListeners() {
        mViewModel.movies.observe(this) {
            setupAdapter(it)
        }
    }

    private fun setupAdapter(movies: MutableList<Filmes>) {
        mViewDataBinding.reciclerview.adapter = FilmesAdapter(movies,
            object : FilmesAdapter.OnClickItem<FilmesAdapter.FilmesViewHolder> {
                override fun onSelectItem(viewHolderItem: Filmes) {
                    DetalhesFilme()

                }
            })
        mViewDataBinding.reciclerview.layoutManager = GridLayoutManager(applicationContext, 3)
    }

    private fun DetalhesFilme() {
        val detalhesFilmes =
            Intent(this, DetalhesFilme::class.java);
        startActivity(detalhesFilmes);
    }

    // Criar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater;
        inflate.inflate(R.menu.menu_principal, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deslogar -> {
                mViewModel.doLogout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun retornarTelaLogin() {
        val telaLogin = Intent(this, FormLogin::class.java)
        startActivity(telaLogin);
        finish();
    }

    override fun logoutSuccess() {
        retornarTelaLogin()
    }

    override fun onError(error: String) {
        showToast(error)
    }
}