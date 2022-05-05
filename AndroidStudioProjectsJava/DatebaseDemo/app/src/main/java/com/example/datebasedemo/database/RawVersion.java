package com.example.datebasedemo.database;

import android.util.Log;

public class RawVersion {
    /**
     * private SQLiteDatabase sqLiteDatabase;
     *
     *     @Override
     *     protected void onCreate(Bundle savedInstanceState) {
     *         super.onCreate(savedInstanceState);
     *         setContentView(R.layout.activity_main);
     *
     * //        sqLiteDatabase = this.openOrCreateDatabase("Users", Context.MODE_PRIVATE, null);
     * //
     * //        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
     * //
     * //        //sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Nick', 28)");
     * //        //sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Sarah', 32)");
     * //
     * //        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);
     * //        int nameIndex = cursor.getColumnIndex("name");
     * //        int ageIndex = cursor.getColumnIndex("age");
     * //
     * //        cursor.moveToFirst();
     * //
     * //        while (!cursor.isAfterLast()) {
     * //            Log.i("name", cursor.getString(nameIndex));
     * //            Log.i("age", String.valueOf(cursor.getInt(ageIndex)));
     * //
     * //            cursor.moveToNext();
     * //
     * //        }
     *
     *         // initiate database
     *         sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
     *         // setup table
     *         //sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
     *         sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)"); // primary key automatically counts the id value
     *
     *         //sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('WW2', 194)");
     *         //sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Nick', 23)");
     *         //sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Nick', 25)");
     *         String name = "Dave'";
     *         sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Dave', 13)");
     *
     *         //Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE age > 100 AND name = 'Sarah'", null);
     *         //Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%a' LIMIT 1", null); // has an a in his/her name, LIMIT 1 gives 1 query back
     *         Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM newUsers", null);
     *         /*
     *         to delete 1 particular item if there are many items with the same name, you need an additional column with the ID
     *
     *         //sqLiteDatabase.execSQL("DELETE FROM users WHERE name = 'WW2'");
     *         //sqLiteDatabase.execSQL("DELETE FROM newUsers WHERE name = 'Nick' AND id = 2"); // note: id's dont change once a id is 3 it stays 3
     *
     * int userIndex = cursor.getColumnIndex("name");
     *int ageIndex = cursor.getColumnIndex("age");
     *int idIndex = cursor.getColumnIndex("id");
     *
     *
     *cursor.moveToFirst();
     *
     *while(!cursor.isAfterLast())
     *
     *{
     *Log.i("name", cursor.getString(userIndex));
     *Log.i("age", String.valueOf(cursor.getInt(ageIndex)));
     *Log.i("id", String.valueOf(cursor.getInt(idIndex)));
     *
     *
     *cursor.moveToNext();
     *
     *}
     *
    }
     */
}
