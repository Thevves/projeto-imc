package com.example.imcapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import java.util.*

class Cadastro_usuario : AppCompatActivity() {

    lateinit var editEmail : EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var editDataNascimento: EditText
    lateinit var radioFeminino: RadioButton
    lateinit var radioMasculino: RadioButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_imc)

        editEmail = findViewById(R.id.edit_novo_email)
        editSenha = findViewById(R.id.edit_nova_senha)
        editNome = findViewById(R.id.edit_novo_nome)
        editProfissao = findViewById(R.id.edit_nova_profissao)
        editAltura = findViewById(R.id.edit_nova_altura)
        editDataNascimento = findViewById(R.id.edit_nova_data_de_nascimento)
        radioFeminino = findViewById(R.id.radio_feminino)
        radioMasculino = findViewById(R.id.radio_masculino)


        //** abrir um calendário ao clicar no campo data de nascimento
        editDataNascimento.setOnClickListener{
            abrirCalendario()
        }
    }

    private fun abrirCalendario() {

        val calendario = Calendar.getInstance()
        val dia = calendario.get(Calendar.DAY_OF_MONTH)
        val mes = calendario.get(Calendar.MONTH)
        val ano = calendario.get(Calendar.YEAR)


            val dpd = DatePickerDialog(this,
                                        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                                        var mesFinal = ""

                                        if(month < 9){
                                            mesFinal = "0${month + 1}"
                                        }

                                            else{
                                                mesFinal = "${month + 1}"
                                            }

                                            var diaFinal = ""

                                            if (dayOfMonth <10){
                                                diaFinal = "0${dayOfMonth + 1}"
                                            }

                                            else{
                                                diaFinal = "${dayOfMonth + 1}"
                                            }

                                            editDataNascimento.setText("$diaFinal/$mesFinal/$year")

                                        }, ano, mes, dia)

            dpd.show()


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
        editor.putString("senha", editSenha.text.toString())
        editor.putString("nome", editNome.text.toString())
        editor.putString("profissão", editProfissao.text.toString())
        editor.putFloat("altura", editAltura.text.toString().toFloat())
        editor.putString("dataNascimento", editDataNascimento.text.toString())
        editor.putString("sexo", if(radioFeminino.isChecked) "F" else "M")
        editor.apply()

        Toast.makeText(this, "Usuario Cadastrado com sucesso ;D", Toast.LENGTH_SHORT).show()
        finish()

        return true
    }
}