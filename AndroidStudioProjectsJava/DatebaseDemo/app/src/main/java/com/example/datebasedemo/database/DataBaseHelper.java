package com.example.datebasedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.datebasedemo.Item;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";
    private static final String TABLE_NAME = "testTable";
    private static final String COL0 = "id";
    private static final String COL1 = "name";
    private static final String COL2 = "description";

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

    public DataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL1 + " VARCHAR, "
                + COL2 + " TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Log upgrade", "onUpgrade Called");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Save data to table
     *
     * @param name
     * @param description
     * @return true if successful
     */

    public boolean addData(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1, name);
        contentValues.put(COL2, description);

        long result = db.insert(TABLE_NAME, null, contentValues);

        // if inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        }

        Log.d(TAG, "addData: Adding "
                + name + ", "
                + description + ", "
                + " to " + TABLE_NAME);
        return true;
    }

    /**
     *
     * Returns all the data from the database
     *
     * @return Cursor
     */

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    /**
     *
     * Get item id matching the name
     *
     * @param name
     * @return Cursor
     */
    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = " + "'" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 +
                " = '" + newName + "' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";

        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public void removeAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;

        db.execSQL(query);
    }
}
