package com.example.implicitintent

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // deprecated
//        btnClick.setOnClickListener {
//            Intent(Intent.ACTION_GET_CONTENT).also {
//                it.type = "image/*" //"image/jpg" or "image/png"
//                startActivityForResult(it, 0)
//            }
//        }

        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                ivPhoto.setImageURI(it)
            }
        )

        btnClick.setOnClickListener {
            getImage.launch("image/*")
        }
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        if (resultCode == Activity.RESULT_OK && resultCode == 0) {
            val uri = data?.data
        }
    }
}