package com.example.a712_assignment2

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity: AppCompatActivity() {
    private val CAMERA_REQUEST_CODE = 100
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val captureBtn = findViewById<Button>(R.id.captureImageButton)
        imageView = findViewById(R.id.capturedImageView)

        captureBtn.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            val bitmap = data?.extras?.get("data") as? Bitmap
            if (bitmap != null){
                imageView.setImageBitmap(bitmap)
            }
        }
    }

}