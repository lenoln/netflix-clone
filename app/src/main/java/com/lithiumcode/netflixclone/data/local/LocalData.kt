package com.lithiumcode.netflixclone.data.local

import com.lithiumcode.netflixclone.Model.Filmes
import com.lithiumcode.netflixclone.Model.filmes
import com.lithiumcode.netflixclone.R
import javax.inject.Inject

class LocalData @Inject constructor() {

    fun movies(): MutableList<Filmes> = mutableListOf(
        filmes {
            capaFilme = R.drawable.filme1
        },
        filmes {
            capaFilme = R.drawable.filme2
        },
        filmes {
            capaFilme = R.drawable.filme3
        },
        filmes {
            capaFilme = R.drawable.filme4
        },
        filmes {
            capaFilme = R.drawable.filme5
        },
        filmes {
            capaFilme = R.drawable.filme6
        },
        filmes {
            capaFilme = R.drawable.filme7
        },
        filmes {
            capaFilme = R.drawable.filme8
        },
        filmes {
            capaFilme = R.drawable.filme9
        },
        filmes {
            capaFilme = R.drawable.filme10
        },
        filmes {
            capaFilme = R.drawable.filme11
        },
        filmes {
            capaFilme = R.drawable.filme12
        },
    )
}