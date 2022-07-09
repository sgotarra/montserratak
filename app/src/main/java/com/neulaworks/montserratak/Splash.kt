package com.neulaworks.montserratak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {
    private val duracio: Long=3000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //amaguem la barra, pantalla a full
        supportActionBar?.hide()
        //cridem a la funció de canviar activitat
        canviarActivity();

    }

    private fun canviarActivity(){
        Handler().postDelayed({
            //llancem un intent (provem de fer...)
            //Create an intent for a specific component.
            //Parametres:
            //packageContext – A Context of the application package implementing this class.
            //cls – The component class that is to be used for the intent.
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, duracio)



    }
}