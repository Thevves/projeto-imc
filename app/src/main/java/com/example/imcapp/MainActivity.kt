package com.example.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var btnEntrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        val buttonCriarConta = findViewById<Button>(R.id.button_criar_conta)

        editEmail = findViewById(R.id.edit_login_email)
        editSenha = findViewById(R.id.edit_login_senha)
        btnEntrar = findViewById(R.id.btn_entrar)

        btnEntrar.setOnClickListener{
            login()
        }

        buttonCriarConta.setOnClickListener {
            val abrircadastroImc = Intent(this, Cadastro_usuario::class.java)
            startActivity(abrircadastroImc)
        }
    }

    private fun login() {
        val arquivo = getSharedPreferences("cadastro", MODE_PRIVATE)

        val email = arquivo.getString("email", "")
        val senha = arquivo.getString("senha", "")

        val emailDigitado = editEmail.text.toString()
        val senhaDigitada = editSenha.text.toString()

        if(emailDigitado == email && senhaDigitada == senha){

            val intent = Intent(this, DashboardActivity::class.java)

            startActivity(intent)
        }
        else{
            Toast.makeText(this, "usuário ou senha incorreto :(", Toast.LENGTH_SHORT).show()
        }
    }
}