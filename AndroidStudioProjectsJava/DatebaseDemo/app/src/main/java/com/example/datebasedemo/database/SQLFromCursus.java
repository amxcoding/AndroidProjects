package com.example.datebasedemo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLFromCursus {

    private static final String TAG = "SQLFromCursus";
    private static final String TABLE_NAME = "testTable";
    private static final String COL0 = "id";
    private static final String COL1 = "name";
    private static final String COL2 = "description";
    private SQLiteDatabase sqLiteDatabase;

    public SQLFromCursus(Context context) {
        this.sqLiteDatabase = context.openOrCreateDatabase(TABLE_NAME, Context.MODE_PRIVATE, null);
        createDataBase();
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getCOL0() {
        return COL0;
    }

    public static String getCOL1() {
        return COL1;
    }

    public static String getCOL2() {
        return COL2;
    }

    /**
     * create database
     */
    private void createDataBase() {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL1 + " VARCHAR, "
                + COL2 + " TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    /**
     * save data
     * @param name
     * @param description
     */
    public void saveData(String name, String description) {
        String query = "INSERT INTO " + TABLE_NAME + " ("
                + COL1 + ", " + COL2 + ") "
                + " VALUES " + " ("
                + "'" + name + "', " + "'" + description + "')";
        sqLiteDatabase.execSQL(query);
    }

    /**
     * get data
     * @return cursor
     */
    public Cursor getData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }
}
