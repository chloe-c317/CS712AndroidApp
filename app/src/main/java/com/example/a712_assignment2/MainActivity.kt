package com.example.a712_assignment2

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Context.RECEIVER_NOT_EXPORTED
import android.util.Log
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var myReceiver: MyBroadcastReceiver

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

        val startServiceBtn = findViewById<Button>(R.id.startServiceButton)
        startServiceBtn.setOnClickListener{
            Toast.makeText(this, "Start Clicked", Toast.LENGTH_SHORT).show()
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            startForegroundService(serviceIntent)
        }

        val sendBroadcastBtn = findViewById<Button>(R.id.sendBroadcastBtn)
        sendBroadcastBtn.setOnClickListener{
            Log.d("DEBUG_BROADCAST", "Send Broadcast button clicked")
            val intent = Intent("com.example.MY_ACTION").apply{
                setPackage(packageName)
            }
            sendBroadcast(intent)
        }
    }

    override fun onStart(){
        super.onStart()

        Log.d("DEBUG_BROADCAST", "Receiver registered")
        myReceiver = MyBroadcastReceiver()
        val filter = IntentFilter("com.example.MY_ACTION")

        ContextCompat.registerReceiver(
            this,
            myReceiver,
            filter,
            RECEIVER_NOT_EXPORTED
        )
    }

    override fun onStop(){
        super.onStop()
        unregisterReceiver(myReceiver)
        Log.d("DEBUG_BROADCAST", "Receiver unregistered")
    }
}