package com.neulaworks.montserratak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var BTMLOGIN = findViewById<Button>(R.id.BTMLOGIN);
        var BTMREGISTRO = findViewById<Button>(R.id.BTMREGISTRO);

        BTMLOGIN.setOnClickListener(){
            Toast.makeText(this, "click botó login",Toast.LENGTH_LONG).show();
        }

        BTMREGISTRO.setOnClickListener(){
            Toast.makeText(this, "click botó Registre",Toast.LENGTH_LONG).show();
        }

    }
}