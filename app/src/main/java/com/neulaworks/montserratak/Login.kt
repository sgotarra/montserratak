package com.neulaworks.montserratak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Login : AppCompatActivity() {
    //Despleguem les variables que farem servir
    lateinit var  correoLogin : EditText
    lateinit var  passLogin : EditText
    lateinit var  BtnLogin : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Busquem a R els elements als que apunten les variables
        correoLogin =findViewById<EditText>(R.id.correoLogin)
        passLogin =findViewById<EditText>(R.id.passLogin)
        BtnLogin =findViewById<Button>(R.id.BtnLogin)

        BtnLogin.setOnClickListener(){
            //Abans de fer el registre validem les dades
            var email:String = correoLogin.getText().toString()
            var passw:String = passLogin.getText().toString()

            // validació del correu
            // si no es de tipus correu
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                correoLogin.setError("Invalid Mail")
            }
            else if (passw.length<6) {
                passLogin.setError("Password less than 6 chars")
            }
            else
            {
               // aquí farem LOGIN al jugador
            }

        }



    }
}