package com.neulaworks.montserratak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.util.Timer
import kotlin.concurrent.schedule

class Splash : AppCompatActivity() {
    private val duracio: Long=4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //amaguem la barra, pantalla a full
        supportActionBar?.hide()
        //cridem a la funció de canviar activitat
        canviarActivity()

    }

    private fun canviarActivity(){

        /**
        Handler().postDelayed({
            //llancem un intent (provem de fer...)
            //Create an intent for a specific component.
            //Parametres:
            //packageContext – A Context of the application package implementing this class.
            //cls – The component class that is to be used for the intent.
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, duracio)
        */

        //utilitzem una eina no deprecated
        // primer afegim
        // import java.util.Timer
        //import kotlin.concurrent.schedule
        // i ara la funció és la seguent:
        // Delay of 5 sec
        Timer().schedule(duracio){
            saltainici()

        }
    }
    fun saltainici()
    {
        val intent=Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}