package com.example.imcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

class Cadastro_usuario : AppCompatActivity() {

    lateinit var editEmail : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_imc)

        editEmail = findViewById(R.id.edit_novo_email)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_novo_usuario, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

    //Salvar os dados do usuário em um sharedPreferences
        // criar um arquivo sharedPreferences
        //se o arquivo já existir o arquivo será aberto
        val dados = getSharedPreferences("cadastro", MODE_PRIVATE)

        // criar um editor para o arquivo
        val editor= dados.edit()
        editor.putString("email", editEmail.text.toString())
        editor.apply()

        return true
    }
}