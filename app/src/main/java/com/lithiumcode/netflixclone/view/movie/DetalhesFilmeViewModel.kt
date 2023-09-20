package com.lithiumcode.netflixclone.view.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lithiumcode.netflixclone.Model.Filmes
import com.lithiumcode.netflixclone.data.local.LocalData
import com.lithiumcode.netflixclone.view.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetalhesFilmeViewModel @Inject constructor(
    private val localData: LocalData
) : BaseViewModel<DetalhesFilmeViewModel.IDetalhesFilmeViewModel>() {
    interface IDetalhesFilmeViewModel

    private var _movies = MutableLiveData<MutableList<Filmes>>()
    val movies: LiveData<MutableList<Filmes>> get() = _movies

    fun getMovies() {
        _movies.value = localData.movies()
    }

}