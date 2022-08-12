package com.neulaworks.montserratak

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import java.lang.Exception


class Menu : AppCompatActivity() {
    //creem unes variables per comprovar ususari i authentificació
    lateinit var auth: FirebaseAuth
    var user:FirebaseUser? = null;

    lateinit var tancarSessio: Button
    lateinit var CreditsBtn: Button
    lateinit var PuntuacionsBtn: Button
    lateinit var jugarBtn: Button
    lateinit var editarBtn: Button

    lateinit var miPuntuaciotxt: TextView
    lateinit var puntuacio: TextView
    lateinit var uid: TextView
    lateinit var correo: TextView
    lateinit var nom: TextView
    lateinit var edat: TextView
    lateinit var poblacio: TextView

    lateinit var imatgePerfil: ImageView

    private var nivell ="1"



    //reference serà el punter que ens envia a la base de dades de jugadors
    lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        auth= FirebaseAuth.getInstance()
        user =auth.currentUser
        //recupera el usuari

        // Creem un punter a la base de dades i li donem un nom
        var database: FirebaseDatabase = FirebaseDatabase.getInstance("https://montserratak-76f14-default-rtdb.europe-west1.firebasedatabase.app/")
        reference = database.getReference("DATA BASE JUGADORS")

        //Aquí creem un tipus de lletra a partir de una font
        val tf = Typeface.createFromAsset(assets,"fonts/mars.ttf")


        tancarSessio =findViewById<Button>(R.id.tancarSessio)
        CreditsBtn =findViewById<Button>(R.id.CreditsBtn)
        PuntuacionsBtn =findViewById<Button>(R.id.PuntuacionsBtn)
        jugarBtn =findViewById<Button>(R.id.jugarBtn)
        editarBtn = findViewById<Button>(R.id.editarBtn)

        //busquem els textos
        miPuntuaciotxt=findViewById(R.id.miPuntuaciotxt)
        puntuacio=findViewById(R.id.puntuacio)
        uid=findViewById(R.id.uid)
        correo=findViewById(R.id.correo)
        nom=findViewById(R.id.nom)
        edat=findViewById(R.id.edat)
        poblacio=findViewById(R.id.poblacio)

        imatgePerfil=findViewById(R.id.imatgePerfil)


        //els hi assignem el tipus de lletra
        miPuntuaciotxt.setTypeface(tf)
        puntuacio.setTypeface(tf)
        uid.setTypeface(tf)
        //correo.setTypeface(tf)
        //nom.setTypeface(tf)

        //fem el mateix amb el text dels botons
        tancarSessio.setTypeface(tf)
        CreditsBtn.setTypeface(tf)
        PuntuacionsBtn.setTypeface(tf)
        jugarBtn.setTypeface(tf)
        editarBtn.setTypeface(tf)

        consulta()

        jugarBtn.setOnClickListener(){

            //hem d'enviar el id, el nom i el contador, i el nivell
            var Uids : String =  uid.getText().toString()
            var noms : String = nom.getText().toString()
            var puntuacios : String = puntuacio.getText().toString()
            var nivells : String = nivell

            Log.i("DEBUG", "UID enviat a MENU:")
            Log.i("DEBUG", Uids)
            Log.i("DEBUG", "NOM enviat a MENU:")
            Log.i("DEBUG", noms)
            Log.i("DEBUG", "Puntuacio enviat a MENU:")
            Log.i("DEBUG", puntuacios)

            val intent= Intent(this, SeleccioNivell::class.java)
            intent.putExtra("UID",Uids)
            intent.putExtra("NOM",noms)
            intent.putExtra("PUNTUACIO",puntuacios)
            intent.putExtra("NIVELL",nivells)
            startActivity(intent)
            finish()


        }

        editarBtn.setOnClickListener(){
            Toast.makeText(this,"EDITAR", Toast.LENGTH_SHORT).show()
        }


        CreditsBtn.setOnClickListener(){

            Toast.makeText(this,"Credits", Toast.LENGTH_SHORT).show()
        }
        PuntuacionsBtn.setOnClickListener(){
            Toast.makeText(this,"Puntuacions", Toast.LENGTH_SHORT).show()
        }


        tancarSessio.setOnClickListener(){
                tancalaSessio()
        }

    }

    private fun tancalaSessio() {
            auth.signOut()  //tanca la sessió
            //va a la pantalla inicial
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
    }

    // Aquest mètode s'executarà quan s'obri el menu
    override fun onStart() {
        usuariLogejat()
        super.onStart()

    }

    private fun usuariLogejat() {

        if (user !=null)
        {
            Toast.makeText(this,"Jugador logejat", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun consulta(){
        //Amb Firebase no fem consultes realment, el que fem en connectar-nos a una referencia
        // i aquesta anirà canviant automàticament quan canvii la base de dades
        // reference apunta a "DATA BASE JUGADORS"
        // sempre es crea un referencia a un punt del arbre de la base de dades
        // Per exemple si tenim la base de dades
        // arrel
        //  - nivell dos
        //      - nivell dos.1
        //                - nom: "pepe"
        //                - dni: "1231212121"
        //      - nivell dos.2: "34"
        //      - nivell dos.3: "36"
        //var bdasereference:DatabaseReference = FirebaseDatabase.getInstance().getReference()
        //    .child("nivell dos")
        //    .child("nivell dos.1")
        // Ara estariem al novell dos.1 del arbre
        // Ens podem subscriure amb un listener que té dos métodes
        //      onDataChange (es crida si s'actualitza les dades o és la primera vegada que ens suscribim)
        //      onCancelled Es crida si hi ha un error o es cancel·la la lectura
        // al primer métode rebrem un objecte json que és la branca sobre la que demanem actualització
        // getkey retorna la clave del objecte
        // getValue retorna el valor
        // els subnodes (fills) es recuperen amb getChildren
        //             es poden llegir com un llistat d'objectes Datasnapshots
        //             o navegar a subnodes concrets amb child("nomdelsubnode")


        var database: FirebaseDatabase = FirebaseDatabase.getInstance("https://montserratak-76f14-default-rtdb.europe-west1.firebasedatabase.app/")
        var bdreference:DatabaseReference = database.getReference("DATA BASE JUGADORS")
        bdreference.addValueEventListener (object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                    Log.i ("DEBUG","arrel value"+ snapshot.getValue().toString())
                    Log.i ("DEBUG","arrel key"+ snapshot.key.toString())
                    // ara capturem tots els fills
                    var trobat:Boolean =false
                    for (ds in snapshot.getChildren()) {
                        Log.i ("DEBUG","DS key: "+ds.child("Uid").key.toString())
                        Log.i ("DEBUG","DS value: "+ds.child("Uid").getValue().toString())
                        Log.i ("DEBUG","DS data: "+ds.child("Data").getValue().toString())
                        Log.i ("DEBUG","DS mail: "+ds.child("Email").getValue().toString())
                        //mirem si el mail és el mateix que el del jugador
                        //si és així, mostrem les dades als textview corresponents
                        if (ds.child("Email").getValue().toString().equals(user?.email)){
                            trobat=true
                            //carrega els textview

                            puntuacio.setText( ds.child("Puntuacio").getValue().toString())
                            uid.setText( ds.child("Uid").getValue().toString())
                            correo.setText( ds.child("Email").getValue().toString())
                            nom.setText( ds.child("Nom").getValue().toString())
                            nivell = ds.child("Nivell").getValue().toString()
                            poblacio.setText( ds.child("Poblacio").getValue().toString())
                            edat.setText( ds.child("Edat").getValue().toString())
                            var imatge: String  = ds.child("Imatge").getValue().toString()

                            try {
                                Picasso.get().load(imatge).into(imatgePerfil)
                            } catch (e:Exception){
                                Picasso.get().load(R.drawable.jugador).into(imatgePerfil)
                            }

                        }
                        if (!trobat)
                        {
                            Log.e ("ERROR","ERROR NO TROBAT MAIL")
                        }

                    }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e ("ERROR","ERROR DATABASE HA CANCEL·LAT ACCIÓ")

            }
        })


    }

}