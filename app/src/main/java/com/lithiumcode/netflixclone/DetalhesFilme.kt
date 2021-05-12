package com.lithiumcode.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.lithiumcode.netflixclone.Adapter.FilmesAdapter
import com.lithiumcode.netflixclone.Model.addFilmes
import com.lithiumcode.netflixclone.OnClick.OnItemClickListener
import com.lithiumcode.netflixclone.OnClick.addOnItemClickListener
import com.lithiumcode.netflixclone.databinding.ActivityDetalhesFilmeBinding

class DetalhesFilme : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmeBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide();
        Toolbar();

        var recyclerFilmesSeries = binding.reciclerviewFilmesSeries;
        recyclerFilmesSeries.adapter = FilmesAdapter(addFilmes());
        recyclerFilmesSeries.layoutManager = GridLayoutManager(applicationContext, 3);

//        recyclerFilmesSeries.addOnItemClickListener(object: OnItemClickListener{
//            override fun onItemClicked(position: Int, view: View) {
//
//            }
//
//        })
    }

    private fun Toolbar() {
        val toolbar_detalhes = binding.toolbarDetalhes;
        toolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar));
        toolbar_detalhes.setNavigationOnClickListener{
            val home = Intent(this, Home::class.java);
            startActivity(home);
        }
    }

}