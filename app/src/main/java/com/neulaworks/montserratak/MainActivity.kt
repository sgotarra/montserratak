package com.neulaworks.montserratak

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    //per a comprovar si la sessió esta inicialitzada
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //assigna valor a user
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        //Aquí creem un tipus de lletra a partir de una font
        val tf = Typeface.createFromAsset(assets,"fonts/mars.ttf")

        var BTMLOGIN = findViewById<Button>(R.id.BTMLOGIN);
        var BTMREGISTRO = findViewById<Button>(R.id.BTMREGISTRO);

        BTMLOGIN.setTypeface(tf)
        BTMREGISTRO.setTypeface(tf)

        BTMLOGIN.setOnClickListener() {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        BTMREGISTRO.setOnClickListener() {
            //Toast.makeText(this, "click botó Registre",Toast.LENGTH_LONG).show();
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }


    // Aquest mètode s'executarà quan s'obri el mainactivity
    override fun onStart() {
        usuariLogejat()
        super.onStart()

    }

    private fun usuariLogejat() {
    //si esta logejat l'envia de pet al menú
        if (user !=null)
        {
            val intent= Intent(this, Menu::class.java)
            startActivity(intent)
            finish()

        }


    }
    





}