package com.example.a712_assignment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val explicitBtn = findViewById<Button>(R.id.explicitButton)
        explicitBtn.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }

        val implicitBtn = findViewById<Button>(R.id.implicitButton)
        implicitBtn.setOnClickListener{
            val intent = Intent("com.example.a712_assignment2.OPEN_SECOND")
            startActivity(intent)
        }

    }

//    fun buttonOnClick(view: View?){
//        println("Button clicked!")
//    }
}