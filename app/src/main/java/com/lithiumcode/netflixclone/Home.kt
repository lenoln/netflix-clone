package com.lithiumcode.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.lithiumcode.netflixclone.Adapter.FilmesAdapter
import com.lithiumcode.netflixclone.Model.addFilmes
import com.lithiumcode.netflixclone.OnClick.OnItemClickListener
import com.lithiumcode.netflixclone.OnClick.addOnItemClickListener
import com.lithiumcode.netflixclone.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler_filmes = binding.reciclerview;
        recycler_filmes.adapter = FilmesAdapter(addFilmes());
        recycler_filmes.layoutManager = GridLayoutManager(applicationContext, 3);

        recycler_filmes.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                when{
                    position == 0 -> DetalhesFilme();
                }
            }
        })

    }

    private fun DetalhesFilme() {
        val detalhesFilmes = Intent(this, DetalhesFilme::class.java);
        startActivity(detalhesFilmes);
    }

    // Criar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater;
        inflate.inflate(R.menu.menu_principal, menu);
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.deslogar -> {
                FirebaseAuth.getInstance().signOut();
                retornarTelaLogin();
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun retornarTelaLogin() {
        val telaLogin = Intent(this, FormLogin::class.java)
        startActivity(telaLogin);
        finish();
    }
}