package com.lithiumcode.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.lithiumcode.netflixclone.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide();
        Toolbar();

        binding.btnCadastrar.setOnClickListener {
            val email = binding.editTextEmail.text.toString();
            val senha = binding.editTextSenha.text.toString();
            val mensagemErro = binding.mensagemErro;

            if(email.isEmpty() || senha.isEmpty()) {
                mensagemErro.setText("Preencha todos os campos!");
                mensagemErro.setTextColor(getColor(R.color.red));
            }else {
                CadastrarUsuario();
            }
        }
    }

    private fun Toolbar() {
        val toolbar = binding.toolbarCadastro;
        toolbar.setBackgroundColor(getColor(R.color.white));
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo));
    }

    private fun CadastrarUsuario() {
        val email = binding.editTextEmail.text.toString();
        val senha = binding.editTextSenha.text.toString();
        val mensagemErro = binding.mensagemErro;

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {

            if(it.isSuccessful) {
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                binding.editTextEmail.setText("");
                binding.editTextSenha.setText("");
                binding.mensagemErro.setText("");
            }
        }.addOnFailureListener {
            var erro = it;

            when{
                erro is FirebaseAuthWeakPasswordException -> mensagemErro.setText("Digite uma senha com no mínimo 6 caracteres");
                erro is FirebaseAuthUserCollisionException -> mensagemErro.setText("Este usuário já foi cadastrado");
                erro is FirebaseNetworkException -> mensagemErro.setText("Sem conexão com a internet");

                else -> mensagemErro.setText("Erro ao cadastrar usuário");
            }
        }
    }

    private fun retornarTelaLogin() {
        val telaLogin = Intent(this, FormLogin::class.java);
        startActivity(telaLogin);
    }

}