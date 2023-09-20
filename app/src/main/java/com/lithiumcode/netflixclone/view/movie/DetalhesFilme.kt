package com.lithiumcode.netflixclone.view.movie

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lithiumcode.netflixclone.view.adapter.FilmesAdapter
import com.lithiumcode.netflixclone.Model.Filmes
import com.lithiumcode.netflixclone.R
import com.lithiumcode.netflixclone.databinding.ActivityDetalhesFilmeBinding
import com.lithiumcode.netflixclone.view.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalhesFilme : BaseActivity<ActivityDetalhesFilmeBinding>(),
    DetalhesFilmeViewModel.IDetalhesFilmeViewModel {

    private val mViewModel: DetalhesFilmeViewModel by viewModels()
    override fun contentLayoutId(): Int = R.layout.activity_detalhes_filme

    override fun initViewBinding() {
        mViewModel.setNavigator(this)
        supportActionBar!!.hide()
        setupToolbar()
        setListeners()
        mViewModel.getMovies()

    }

    private fun setListeners() {
        mViewModel.movies.observe(this) {
            setupAdapter(it)
        }
    }

    private fun setupAdapter(movies: MutableList<Filmes>) {
        mViewDataBinding.apply {
            reciclerviewFilmesSeries.adapter = FilmesAdapter(movies,
                object : FilmesAdapter.OnClickItem<FilmesAdapter.FilmesViewHolder> {
                    override fun onSelectItem(viewHolderItem: Filmes) {
                        showToast("Clicou em um filme")
                    }
                })

            reciclerviewFilmesSeries.layoutManager =
                GridLayoutManager(applicationContext, 3)
        }
    }

    private fun setupToolbar() {
        val toolbarDetalhes = mViewDataBinding.toolbarDetalhes;
        toolbarDetalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar));
        toolbarDetalhes.setNavigationOnClickListener {
            finish()
        }
    }

}