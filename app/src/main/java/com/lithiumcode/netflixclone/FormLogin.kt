package com.lithiumcode.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IntegerRes
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.lithiumcode.netflixclone.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater);
        setContentView(binding.root)

        supportActionBar!!.hide();
        VerificarUsuarioLogado();

        val buttonEntrar = binding.btnEntrar;
        val txtInscrevaSe = binding.txtInscrevaSe;

        txtInscrevaSe.setOnClickListener {
            val viewCadastro = Intent(this, FormCadastro::class.java);
            startActivity(viewCadastro);
        }

        buttonEntrar.setOnClickListener {
            var email = binding.txtEmail.text.toString();
            var senha = binding.txtSenha.text.toString();
            val mensagem = binding.mensagemErro;

            if(email.isEmpty()) {
                mensagem.setText("Digite seu email");
                mensagem.setTextColor(getColor(R.color.orange));
            }else if(senha.isEmpty()) {
                mensagem.setText("Digite sua senha");
                mensagem.setTextColor(getColor(R.color.orange));
            }else {
                AutenticarUsuario();
            }
        }
    }

    private fun AutenticarUsuario() {
        val email = binding.txtEmail.text.toString();
        val senha = binding.txtSenha.text.toString();
        val mensagemErro = binding.mensagemErro;

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    IrParaHome();
                }
            }.addOnFailureListener {
                var erro = it;

                when{
                    erro is FirebaseAuthInvalidCredentialsException -> mensagemErro.setText("E-mail ou Senha estão incorretos!");
                    erro is FirebaseNetworkException -> mensagemErro.setText("Sem conexão com a internet!");
                    else -> mensagemErro.setText("Erro ao logar na aplicação!");
                }
            }
    }

    private fun VerificarUsuarioLogado() {
        val usuarioLogado = FirebaseAuth.getInstance().currentUser;

        if(usuarioLogado != null) {
            IrParaHome();
        }
    }

    private fun IrParaHome() {
        val home = Intent(this, Home::class.java);
        startActivity(home);
        finish();
    }

}