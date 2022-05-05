package com.example.customtoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.snackbar.Snackbar
import android.view.View

import com.google.android.material.snackbar.Snackbar.SnackbarLayout


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToast.setOnClickListener {
//            Toast(this).apply {
//                duration = Toast.LENGTH_LONG
//                view = layoutInflater.inflate(R.layout.toast, clToast)
//                show()

            //Toast.makeText(this, "toast", Toast.LENGTH_LONG).show()

//            Toast.makeText(applicationContext,
//                HtmlCompat.fromHtml("<font color='red'>custom toast message</font>", HtmlCompat.FROM_HTML_MODE_LEGACY),
//                Toast.LENGTH_LONG).show()

            val snackbar = Snackbar.make(clToast, "This is Simple Snackbar", Snackbar.LENGTH_SHORT)
            snackbar.show()


        }
    }
}