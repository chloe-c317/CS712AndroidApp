package com.example.a712_assignment2

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {
    private lateinit var myReceiver: MyBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if(checkSelfPermission("com.example.a712_assignment2.MSE712") != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf("com.example.a712_assignment2.MSE712"), 712)
        }
        enableButtons()
    }

    // Implemented enableButtons() to move direct button behavior from onCreate
    private fun enableButtons(){
        val explicitBtn = findViewById<Button>(R.id.explicitButton)
        explicitBtn.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        val implicitBtn = findViewById<Button>(R.id.implicitButton)
        implicitBtn.setOnClickListener {
            startActivity(Intent("com.example.a712_assignment2.OPEN_SECOND"))
        }

        val startServiceBtn = findViewById<Button>(R.id.startServiceButton)
        startServiceBtn.setOnClickListener{
            Toast.makeText(this, "Start Clicked", Toast.LENGTH_SHORT).show()
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                startForegroundService(serviceIntent)
            }else{
                startService(serviceIntent)
            }
        }

        val sendBroadcastBtn = findViewById<Button>(R.id.sendBroadcastBtn)
        sendBroadcastBtn.setOnClickListener{
            Log.d("DEBUG_BROADCAST", "Send Broadcast button clicked")
            val intent = Intent("com.example.MY_ACTION").apply{
                setPackage(packageName)
            }
            sendBroadcast(intent)
        }

        val viewImageBtn = findViewById<Button>(R.id.imageActivityBtn)
        viewImageBtn.setOnClickListener{
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
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
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    override fun onStop(){
        super.onStop()
        unregisterReceiver(myReceiver)
        Log.d("DEBUG_BROADCAST", "Receiver unregistered")
    }
}