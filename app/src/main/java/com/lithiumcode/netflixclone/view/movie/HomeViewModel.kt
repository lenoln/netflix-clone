package com.lithiumcode.netflixclone.view.movie

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.lithiumcode.netflixclone.Model.Filmes
import com.lithiumcode.netflixclone.R
import com.lithiumcode.netflixclone.data.local.LocalData
import com.lithiumcode.netflixclone.view.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val context: Context,
    private val localData: LocalData
): BaseViewModel<HomeViewModel.IFilmeListViewModel>() {
    interface IFilmeListViewModel{
        fun logoutSuccess()
        fun onError(error: String)
    }

    private var _movies = MutableLiveData<MutableList<Filmes>>()
    val movies: LiveData<MutableList<Filmes>> get() = _movies

    fun getMovies(){
        _movies.value = localData.movies()
    }

    fun doLogout() {
        FirebaseAuth.getInstance().signOut()

        val checkLogin = FirebaseAuth.getInstance().currentUser
        if(checkLogin == null)
            navigator?.logoutSuccess()
        else
            navigator?.onError(context.getString(R.string.firebase_generic_error))

    }
}