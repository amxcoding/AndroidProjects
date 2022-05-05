package com.example.sqlitedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private var stdList: List<StudentModel> = ArrayList()
    private lateinit var sqLiteHelper: SQLiteHelper
    private var studentAdapter: StudentAdapter? = null
    private var viewIsChecked = false
    private var studentModel: StudentModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set sql and recyclerview
        sqLiteHelper = SQLiteHelper(this)
        initRecyclerView()

        onClicks()

        binding.etName.id
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_topbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var message: String
        when(item.itemId) {
            R.id.miDeleteAll -> {
                getStudents()
                if (!stdList.isEmpty()) {
                    var builder = AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to delete all records?")
                        .setCancelable(true)
                        .setPositiveButton("Yes") { dialog, _ ->
                            sqLiteHelper.deleteAll()
                            message = "All records deleted"
                            toastMaker(message)
                            getStudents()
                            dialog.dismiss()
                        }
                        .setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()

                    builder.show()
                } else {
                    message = "Nothing to delete"
                    toastMaker(message)
                }
            }
            R.id.miInfo -> {
                Intent(this, SecondActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return true
    }

    private fun onClicks() {
        // ADD
        binding.btnAdd.setOnClickListener { addStudent() }

        // VIEW
        binding.btnView.setOnClickListener {
            getStudents()

            if (!viewIsChecked) {
                viewIsChecked = true
                binding.btnView.text = "Hide List"
                binding.recyclerView.visibility = View.VISIBLE
            } else {
                viewIsChecked = false
                binding.btnView.text = "View"
                binding.recyclerView.visibility = View.INVISIBLE
            }
        }

        // UPDATE
        binding.btnUpdate.setOnClickListener { updateStudent() }

        // Get and change name and email
        studentAdapter?.setOnClickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            binding.etName.setText(it.name)
            binding.etEmail.setText(it.email)
            studentModel = it
        }

        // Delete
        studentAdapter?.setOnClickDeleteItem {
            deleteStudent(it.id)

        }


    }

    private fun deleteStudent(id: Int) {
        var message: String
        val builder = AlertDialog.Builder(this)
            .setMessage("Are you sure you want to delete item?")
            .setCancelable(true)
            .setPositiveButton("Yes") { dialog, _ ->
                val status = sqLiteHelper.deleteStudent(id)

                if (status > -1) {
                    message = "Deleted successfully"
                    getStudents()
                } else {
                    message = "Could not delete..."
                }
                toastMaker(message)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        builder.show()

    }

    private fun  updateStudent() {
        var message: String
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()

        // Check record not changed
        if (name == studentModel?.name && email == studentModel?.email) {
            message = "Record not changed..."
            toastMaker(message)
            return
        }

        if (studentModel == null) return

        val std = StudentModel(id = studentModel!!.id, name = name, email = email)
        val status = sqLiteHelper.updateStudent(std)

        if (status > -1) {
            message = "Update successful"
            clearEditText()
            getStudents()
        } else {
            message = "Update failed..."
        }
        toastMaker(message)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter()
        binding.recyclerView.adapter = studentAdapter
    }

    private fun getStudents() {
        stdList = sqLiteHelper.getAllStudents()
        studentAdapter?.setStudentList(stdList)
        Log.d(TAG, "getStudents: ${stdList.size}")
    }

    private fun addStudent() {
        var message: String
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()

        if (name.isEmpty() || email.isEmpty()) {
            message = "Please enter required field"
            toastMaker(message)
        } else {
            val std = StudentModel(name = name, email = email)
            val status = sqLiteHelper.insertStudent(std)

            // check insert successful or not
            if (status > -1) {
                message = "Student $name added"
                // clear editTexts
                clearEditText()
                // update view
                getStudents()
            } else {
                message = "Record not saved"
            }
            toastMaker(message)

        }
    }

    private fun clearEditText() {
        binding.etName.setText("")
        binding.etEmail.setText("")
        binding.etName.requestFocus()
    }

    private fun toastMaker(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}