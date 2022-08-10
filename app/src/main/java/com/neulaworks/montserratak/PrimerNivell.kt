package com.neulaworks.montserratak

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.okhttp.internal.DiskLruCache
import org.w3c.dom.Text
import java.text.SimpleDateFormat
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

lateinit var btnStart: Button
lateinit var puntuacioTxt: TextView
lateinit var countdownTxt: TextView

lateinit var continuarBtn: Button

private var NOM: String =""
private var PUNTUACIO: String=""
private var UID: String=""
private var NIVELL: String=""


var puntsactuals: Int =0   //els punts actuals del jugador


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

        continuarBtn = findViewById(R.id.continuarBtn)

        btnStart = findViewById(R.id.btnStart)
        puntuacioTxt = findViewById(R.id.puntuacioTxt)
        countdownTxt = findViewById(R.id.countdownTxt)

        var intent:Bundle? = intent.extras
        UID = intent?.get("UID").toString()
        NOM = intent?.get("NOM").toString()
        PUNTUACIO = intent?.get("PUNTUACIO").toString()
        NIVELL = intent?.get("NIVELL").toString()
        Log.i("DEBUG", "UID rebut:")
        Log.i("DEBUG", UID)




        val tf = Typeface.createFromAsset(assets,"fonts/mars.ttf")
        countdownTxt.setTypeface(tf)
        puntuacioTxt.setTypeface(tf)
        btnStart.setTypeface(tf)
        continuarBtn.setTypeface(tf)

        continuarBtn.visibility = View.INVISIBLE



        time_in_milli_seconds = 100000L  //100 segons

        var fondo:ImageView = findViewById(R.id.fondomontse)
        if (NIVELL.compareTo("2")==0) { fondo.setImageResource(R.drawable.fondomonestir)}
        if (NIVELL.compareTo("3")==0) { fondo.setImageResource(R.drawable.fondocel)}

        if (NIVELL.compareTo("1")==0) {
            puntsactuals = 0
            //si es el nivell 1 llavors posaa els punts a 0
            }else {
            puntsactuals=PUNTUACIO.toInt()
            }


        puntuacioTxt.setText(puntsactuals.toString())
        mostra_imatges()


        continuarBtn.setOnClickListener(){
            //va a la pantalla inicial
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnStart.setOnClickListener(){
            // posem en marxa el timer
            startTimer (time_in_milli_seconds)
            // esborrem el botó
            btnStart.visibility = View.INVISIBLE
          }

        imbtn1.setOnClickListener(){
            //mirem si te un alien, un humà o res
            if (contingut_botons.get(0).equals(1))
            {
                //es un alien,pinta sang d'alien
                imbtn1.setImageResource(R.drawable.sangalien)
                //incrementa el comptador en 10
                contador(10)
            }
            if (contingut_botons.get(0).equals(2))
            {
                //es un humà, pinta sang d'humà
                imbtn1.setImageResource(R.drawable.sanghuma)
                //decrementa el comptador en -50
                contador(-50)
            }
            //posem el contingut de boto a 0 (sinó si fes doble click marcaria doble)
            contingut_botons.set(0,0)
        }

        imbtn2.setOnClickListener(){
             if (contingut_botons.get(1).equals(1))
            {
                imbtn2.setImageResource(R.drawable.sangalien)
                 contador(10)
            }
           contingut_botons.set(1,0)
        }

        imbtn3.setOnClickListener(){
            if (contingut_botons.get(2).equals(1))
            {
                imbtn3.setImageResource(R.drawable.sangalien)
                contador(10)
            }
            if (contingut_botons.get(2).equals(2))
            {
                imbtn3.setImageResource(R.drawable.sanghuma)
                contador(-50)
            }
            contingut_botons.set(2,0)
        }
        imbtn4.setOnClickListener(){
            if (contingut_botons.get(3).equals(1))
            {
                imbtn4.setImageResource(R.drawable.sangalien)
                contador(10)
            }
            if (contingut_botons.get(3).equals(2))
            {
                imbtn4.setImageResource(R.drawable.sanghuma)
                contador(-50)
            }
            contingut_botons.set(3,0)
        }
        imbtn5.setOnClickListener(){
            if (contingut_botons.get(4).equals(1))
            {
                imbtn5.setImageResource(R.drawable.sangalien)
                contador(10)
            }
            if (contingut_botons.get(4).equals(2))
            {
                imbtn5.setImageResource(R.drawable.sanghuma)
                contador(-50)
            }
            contingut_botons.set(4,0)
        }
        imbtn6.setOnClickListener(){
            if (contingut_botons.get(5).equals(1))
            {
                imbtn6.setImageResource(R.drawable.sangalien)
                contador(10)
            }
            if (contingut_botons.get(5).equals(2))
            {
                imbtn6.setImageResource(R.drawable.sanghuma)
                contador(-50)
            }
            contingut_botons.set(5,0)
        }

    }


    private fun contador(increment: Int) {
        puntsactuals = puntsactuals + increment
        var punts:String = getString(R.string.punts)
        puntuacioTxt.setText(puntsactuals.toString()+" "+ punts)
    }

    private fun startTimer(time_in_mili_seconds: Long)
    {
        // countdowninterval es el temps que anirem passant per onTick
        countdown_timer = object: CountDownTimer (time_in_mili_seconds,1000) {
            override fun onFinish() {
                //Ha acabat el comptador
                Log.i("DEBUG", "FINAL DEL temporitzador")
                finalNivell()
            }

            override fun onTick(millisUntilFinished: Long) {
                // mostro el valor a countdownTxt
                var segonsCanvi =3f
                val segons:Long = millisUntilFinished/1000
                countdownTxt.setText(segons.toString())
                Log.i("DEBUG", "COUNTDOWN")

                if (NIVELL.compareTo("2")==0) { segonsCanvi=2f}
                if (NIVELL.compareTo("3")==0) { segonsCanvi=1f}
                //cada x segons canvio_imatges
                if ((segons.toFloat() % segonsCanvi)==0f)canvio_imatges()
            }
        }
        countdown_timer.start()
    }

    private fun finalNivell(){
        var database: FirebaseDatabase = FirebaseDatabase.getInstance("https://montserratak-76f14-default-rtdb.europe-west1.firebasedatabase.app/")
        var reference: DatabaseReference = database.getReference("DATA BASE JUGADORS")

        //captura la data
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance()
        val formatedDate = formatter.format(date)

        var nivell:String ="1"
        var guanya: Boolean =false //true si es guanya
        var fondo:ImageView = findViewById(R.id.fondomontse)

        if (NIVELL.toInt()==1 && puntsactuals>300){ guanya=true}
        if (NIVELL.toInt()==2 && puntsactuals>600){ guanya =true}
        if (NIVELL.toInt()==3 && puntsactuals>1000){guanya =true}

        if (guanya){
            Log.i ("DEBUG","mostra victory")
            //canvia la imatge per victory
            fondo.setImageResource(R.drawable.victory)
            if (NIVELL.toInt()==1) {nivell="2"}
            if (NIVELL.toInt()==2) {nivell="3"}
            if (NIVELL.toInt()==3) {nivell="1"}   //Torna a començar
        }
        else{
            Log.i ("DEBUG","mostra defeat")
            fondo.setImageResource(R.drawable.defeat)
            nivell="1"
            //canvia la imatge per defeat
        }


        //grava les dades del jugador  (puntuació, nivell i Data)
        //accedint directament al punt del arbre de dades que volem anar, podem modificar
        //només una de les dades sense que calgui canviar tots els camps: nom, email...
        reference.child(UID).child("Puntuacio").setValue(puntsactuals.toString())
        reference.child(UID).child("Nivell").setValue(nivell)
        reference.child(UID).child("Data").setValue(formatedDate)

        //posa tots els valors dels imagebuttons a 0
        for (position in contingut_botons.indices)  contingut_botons.set(position,0)

        //crida al mètode de mostra imatges
        mostra_imatges()

        //ja hem gravat les dades
        //ara mostrem el botó de continuar
        Log.i ("DEBUG","mostra boto continuar")
        continuarBtn.visibility = View.VISIBLE
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

    private fun canvio_imatges() {

        actualizto_array()
        mostra_imatges()
    }

    private fun mostra_imatges(){

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