package com.example.booklibraryapp.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import android.content.ContentValues

class MyDataBaseHelper(
    context: Context?,
    DATABASE_NAME: String? = "BookLibrary.db",
    DATABASE_VERSION: Int = 1
): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val TABLE_NAME = "my_library"
    private val COLUMN_ID = "id";
    private val COLUMN_TITLE = "book_title";
    private val COLUMN_AUTHOR = "book_author";
    private val COLUMN_PAGES = "book_pages";



    override fun onCreate(sqlDatabase: SQLiteDatabase?) {
       var query =
           "CREATE TABLE $TABLE_NAME (" +
                   "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   "$COLUMN_TITLE TEXT, " +
                   "$COLUMN_AUTHOR TEXT, " +
                   "$COLUMN_PAGES INTEGER);"

        sqlDatabase?.execSQL(query)
    }

    override fun onUpgrade(sqlDatabase: SQLiteDatabase?, p1: Int, p2: Int) {
        sqlDatabase?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(sqlDatabase)
    }

    fun addBook() {
        val sqlDatabase = this.writableDatabase
        var contentValues = ContentValues
    }
}