package com.neulaworks.montserratak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class Registro : AppCompatActivity() {

    //Definim les variables que farem servir
    //lateinit ens permet no inicialitzar-les encara
    lateinit var  correoEt :EditText
    lateinit var  passEt :EditText
    lateinit var  nombreEt :EditText
    lateinit var  fechaTxt :TextView
    lateinit var  Registrar : Button

    lateinit var auth: FirebaseAuth  //FIREBASE AUTENTIFICACIO




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Busquem a R els elements als que apunten les variables
        correoEt =findViewById<EditText>(R.id.correoEt)
        passEt =findViewById<EditText>(R.id.passEt)
        nombreEt =findViewById<EditText>(R.id.nombreEt)
        fechaTxt =findViewById<TextView>(R.id.fechaEt)
        Registrar =findViewById<Button>(R.id.Registrar)

        //Instanciem el firebaseAuth
        auth = FirebaseAuth.getInstance()

        //carreguem la data al TextView
        //Utilitzem calendar (hi ha moltes altres opcions)
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance() //or use getDateInstance()
        val formatedDate = formatter.format(date)
        //ara la mostrem al TextView
        fechaTxt.setText(formatedDate)

        Registrar.setOnClickListener(){
            //Abans de fer el registre validem les dades
            var email:String = correoEt.getText().toString()
            var passw:String = passEt.getText().toString()

            // validació del correu
            // si no es de tipus correu
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                correoEt.setError("Invalid Mail")
            }
            else if (passw.length<6) {
                passEt.setError("Password less than 6 chars")
            }
            else
            {
                RegistrarJugador(email, passw)
            }

        }




    }

    fun RegistrarJugador(email:String, passw:String){
        auth.createUserWithEmailAndPassword(email, passw)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText( this,"createUserWithEmail:success",Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                  Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    fun updateUI(user:FirebaseUser?){
        //hi ha un interrogant perquè podria ser null
        if (user!=null)
        {
            var puntuacio: Int = 0
            var uidString: String = user.uid
            var correoString: String = correoEt.getText().toString()
            var passString: String = passEt.getText().toString()
            var nombreString: String = nombreEt.getText().toString()
            var fechaString: String= fechaTxt.getText().toString()




        }
        else
        {
            Toast.makeText( this,"ERROR CREATE USER",Toast.LENGTH_SHORT).show()
        }
    }


}