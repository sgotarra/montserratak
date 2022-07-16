package com.neulaworks.montserratak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import java.util.*
import kotlin.concurrent.schedule

lateinit var countdown_timer: CountDownTimer
var time_in_milli_seconds = 0L

var contingut_botons = arrayOf(0,0,0,0,0,0)  //poden ser 0 "res",  1 "alien", 2 "human"

//Image Bottons

lateinit var  imbtn1 : ImageButton
lateinit var  imbtn2 : ImageButton
lateinit var  imbtn3 : ImageButton
lateinit var  imbtn4 : ImageButton
lateinit var  imbtn5: ImageButton
lateinit var  imbtn6 : ImageButton



class PrimerNivell : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primer_nivell)


        //busco els 3 butons
        imbtn1 = findViewById(R.id.imbtn1)
        imbtn2 = findViewById(R.id.imbtn2)
        imbtn3 = findViewById(R.id.imbtn3)
        imbtn4 = findViewById(R.id.imbtn4)
        imbtn5= findViewById(R.id.imbtn5)
        imbtn6 = findViewById(R.id.imbtn6)

        time_in_milli_seconds = 100000L  //100 segons
        startTimer (time_in_milli_seconds)

        canvio_imatges()

    }


    private fun startTimer(time_in_mili_seconds: Long)
    {
        // countdowninterval es el temps que anirem passant per onTick
        countdown_timer = object: CountDownTimer (time_in_mili_seconds,1000) {
            override fun onFinish() {
                //Ha acabat el comptador
                Log.i("DEBUG", "FINAL a temporitzador")
            }

            override fun onTick(millisUntilFinished: Long) {
                Log.i("DEBUG", "TIME")
                Log.i ("DEBUG",millisUntilFinished.toString())
                canvio_imatges()
            }
        }
        countdown_timer.start()
    }


    private fun actualizto_array(){
        var r=Random()
        var retornarandom: Int
        for (position in contingut_botons.indices)
        {
            retornarandom=r.nextInt(3)
            //retornarà 0, 1 o 2
            contingut_botons.set(position,retornarandom)
        }
        //fa una comprovació perque la posició tercera no pot ser mai humano (no hi ha dibuix)
        if (contingut_botons.get(2).equals(2)) contingut_botons.set(2,0)
    }

    private fun canvio_imatges(){

        actualizto_array()

        var contingut: Int =0
        for (position in contingut_botons.indices)
        {
            contingut= contingut_botons.get(position)
            if (position==0)
            {
                //boto imbtn1
                if (contingut==0) imbtn1.setImageResource(R.drawable.baseboto)
                if (contingut==1) imbtn1.setImageResource(R.drawable.baltesqet)
                if (contingut==2) imbtn1.setImageResource(R.drawable.bdaltesqpp)
            }
            if (position==1)
            {
                //boto imbtn2
                if (contingut==0) imbtn2.setImageResource(R.drawable.baseboto)
                if (contingut==1) imbtn2.setImageResource(R.drawable.baltcenet)

            }
            if (position==2)
            {
                //boto imbtn3
                if (contingut==0) imbtn3.setImageResource(R.drawable.baseboto)
                if (contingut==1) imbtn3.setImageResource(R.drawable.baltdretet)
                if (contingut==2) imbtn3.setImageResource(R.drawable.bdaltdrepp)
            }

            if (position==3)
            {
                //boto imbtn1
                if (contingut==0) imbtn4.setImageResource(R.drawable.baseboto)
                if (contingut==1) imbtn4.setImageResource(R.drawable.bbaixesquet)
                if (contingut==2) imbtn4.setImageResource(R.drawable.bbaixesqpp)
            }

            if (position==4)
            {
                //boto imbtn1
                if (contingut==0) imbtn5.setImageResource(R.drawable.baseboto)
                if (contingut==1) imbtn5.setImageResource(R.drawable.bbaixcenet)
                if (contingut==2) imbtn5.setImageResource(R.drawable.bbaixcenpp)
            }

            if (position==5)
            {
                //boto imbtn1
                if (contingut==0) imbtn6.setImageResource(R.drawable.baseboto)
                if (contingut==1) imbtn6.setImageResource(R.drawable.bbaixdrepp)
                if (contingut==2) imbtn6.setImageResource(R.drawable.bbaixdrepp)
            }
        }




    }
}