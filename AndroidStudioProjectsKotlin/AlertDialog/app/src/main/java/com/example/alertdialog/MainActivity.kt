package com.example.alertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setMessage("Do you want to add Mr. Poop to your contacts list?")
            .setIcon(R.drawable.ic_add_contact)
            .setPositiveButton("Yes") { _, _ -> //dialogInterface, i: the lambda function takes 2 parameters that we dont use, so replaced by _
                Toast.makeText(this, "You added Mr. Poop to your contact list.", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { _, _ -> //dialogInterface, i: the lambda function takes 2 parameters that we dont use, so replaced by _
                Toast.makeText(this, "You didn't add Mr. Poop to your contact list.", Toast.LENGTH_SHORT).show()
            }
            .create()

        btnDia1.setOnClickListener {
            addContactDialog.show()
        }

        val options = arrayOf("First item", "Second item", "Third item") // options for radio button
        val singleChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Choose one of the options")
            .setSingleChoiceItems(options, 0) { dialogInterface, i ->
                Toast.makeText(this, "You clicked ${options[i]}.", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Accept") { _, _ -> //dialogInterface, i: the lambda function takes 2 parameters that we dont use, so replaced by _
                Toast.makeText(this, "You accepted the single choice dialog.", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline") { _, _ -> //dialogInterface, i: the lambda function takes 2 parameters that we dont use, so replaced by _
                Toast.makeText(this, "You declined the single choice dialog.", Toast.LENGTH_SHORT).show()
            }
            .create()

        btnDia2.setOnClickListener {
            singleChoiceDialog.show()
        }

        val multiChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Choose options")
            .setMultiChoiceItems(options, booleanArrayOf(false, false, false)) { _, i, isChecked -> //dialogInterface, i, b
                if (isChecked) {
                    Toast.makeText(this, "You checked ${options[i]}.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "You unchecked ${options[i]}.", Toast.LENGTH_SHORT).show()
                }

            }
            .setPositiveButton("Accept") { _, _ -> //dialogInterface, i: the lambda function takes 2 parameters that we dont use, so replaced by _
                Toast.makeText(this, "You accepted the multi choice dialog.", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline") { _, _ -> //dialogInterface, i: the lambda function takes 2 parameters that we dont use, so replaced by _
                Toast.makeText(this, "You declined the multi choice dialog.", Toast.LENGTH_SHORT).show()
            }
            .create()

        btnDia3.setOnClickListener {
            multiChoiceDialog.show()
        }

    }
}