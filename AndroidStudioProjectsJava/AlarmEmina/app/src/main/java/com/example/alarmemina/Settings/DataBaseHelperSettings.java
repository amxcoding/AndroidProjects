package com.example.alarmemina.Settings;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelperSettings extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "alarm_settings_table";
    private static final String COL1 = "name";
    private static final String COL2 = "sound";
    private static final String COL3 = "vibration";
    private static final String COL4 = "snooze";
    private static final String COL5 = "mission";

    public DataBaseHelperSettings(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL1 + " TEXT, " + COL2 + " TEXT, "+ COL3 + " TEXT, "+ COL4 + " TEXT, "+ COL5 + " TEXT)";
        sqLiteDatabase.execSQL(createTable);

        addData("", "random", "normal", "5 minutes, 3 times", "none");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addData(String name, String sound, String vibration, String snooze, String mission){

        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

        String sql;
        Cursor cursor = db.rawQuery("SELECT "+COL1+" FROM " + TABLE_NAME + " WHERE " + COL1 + " =?", new String[]{name});

        if(cursor.getCount() > 0)
            sql = "UPDATE " + TABLE_NAME + " SET " +COL1+ " = '" + name + "', " +COL2+ " = '" + sound + "', " +COL3+ " = '" + vibration + "', "
                    +COL4+ " = '" + snooze + "', " +COL5+ " = '" + mission + "'" + " WHERE " +COL1+ " = '"+name+"'";
        else
            sql = "INSERT or replace INTO " + TABLE_NAME + " VALUES ( '" +name+ "', '" +sound+ "', '" +vibration+ "', '" +snooze+ "', '" +mission+ "' )";

        db.execSQL(sql);

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT "+COL1+ ", " +COL2+ ", " +COL3+ ", "
                +COL4+ ", " +COL5+ " FROM "+ TABLE_NAME , null);

        return data;
    }

    public void deleteItem(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1 + "=?", new String[]{name});
    }
}
