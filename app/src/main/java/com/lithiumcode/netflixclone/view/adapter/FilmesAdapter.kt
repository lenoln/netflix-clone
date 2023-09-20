package com.lithiumcode.netflixclone.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lithiumcode.netflixclone.Model.Filmes
import com.lithiumcode.netflixclone.databinding.ListaFilmesBinding

class FilmesAdapter(val filmes: MutableList<Filmes>,
                    private val onClickItem: OnClickItem<FilmesViewHolder>
): RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        val binding = ListaFilmesBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return FilmesViewHolder(binding);
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        with(holder){
            val item = filmes[position]
            with(item){
                binding.capaFilme.setImageResource(capaFilme)
                binding.root.setOnClickListener {
                    onClickItem.onSelectItem(
                        item
                    )
                }
            }
        }
    }

    override fun getItemCount() = filmes.size;

    inner class FilmesViewHolder(val binding: ListaFilmesBinding): RecyclerView.ViewHolder(binding.root){
    }

    interface OnClickItem<viewHolderItem : FilmesViewHolder> {
        fun onSelectItem(viewHolderItem: Filmes)
    }

}