package com.lithiumcode.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ocultar actionbar
        supportActionBar!!.hide();

        // Método para chamar a tela de formLogin após o tempo determinado
        Handler(Looper.getMainLooper()).postDelayed({
            AbrirTelaLogin();
        }, 2000)
    }

    private fun AbrirTelaLogin(){
        val formLogin = Intent(this, FormLogin::class.java);
        startActivity(formLogin);
        finish();
    }
}