package com.example.a712_assignment2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?){
        Log.d("DEBUG_BROADCAST", "Broadcast received!")
        Toast.makeText(context, "Broadcast Received!", Toast.LENGTH_SHORT).show();
    }
}