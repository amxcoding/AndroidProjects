package com.example.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

/*
I can declare properties that need to be inside a super constructor 3 ways:
1. Declare them inside the class constructor
2. Declare them above the class
3. Declare them as static inside companion object
 */
private const val DATABASE_NAME = "student.db" // 2

class SQLiteHelper(
    context: Context // 1
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object { // a companion object is a static final
        private const val DATABASE_VERSION = 1 // 3
        private const val TBL_STUDENT = "tbl_student"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent = ("CREATE TABLE $TBL_STUDENT (" +
                "$ID INTEGER PRIMARY KEY," +
                "$NAME TEXT," +
                "$EMAIL TEXT)")

        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(db)
    }

    fun insertStudent(student: StudentModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, student.id)
        contentValues.put(NAME, student.name)
        contentValues.put(EMAIL, student.email)

        val succes = db.insert(TBL_STUDENT, null, contentValues)
        db.close()
        return succes
    }

    fun getAllStudents(): ArrayList<StudentModel> {
        val stdList: ArrayList<StudentModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_STUDENT"
        val db = this.writableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList() // return null
        }

        var id: Int
        var name: String
        var email: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(ID))
                name = cursor.getString(cursor.getColumnIndex(NAME))
                email = cursor.getString(cursor.getColumnIndex(EMAIL))

                val student = StudentModel(id = id, name = name, email = email)
                stdList.add(student)
            } while (cursor.moveToNext())
        }

        return stdList
    }

    fun updateStudent(student: StudentModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, student.id)
        contentValues.put(NAME, student.name)
        contentValues.put(EMAIL, student.email)

        val succes = db.update(TBL_STUDENT, contentValues, "$ID=${student.id}", null)
        db.close()
        return succes
    }

    fun deleteStudent(id: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val succes = db.delete(TBL_STUDENT, "$ID=$id", null)
        db.close()

        return succes
    }

    fun deleteAll() {
        val db = this.writableDatabase
        val query = "DELETE FROM $TBL_STUDENT"

        db.execSQL(query)
        db.close()
    }
}