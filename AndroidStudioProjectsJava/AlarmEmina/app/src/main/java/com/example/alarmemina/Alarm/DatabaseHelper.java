package com.example.alarmemina.Alarm;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "alarm_table";
    private static final String COL1 = "name";
    private static final String COL2 = "hour";
    private static final String COL3 = "minutes";
    private static final String COL4 = "monday";
    private static final String COL5 = "tuesday";
    private static final String COL6 = "wednesday";
    private static final String COL7 = "thursday";
    private static final String COL8 = "friday";
    private static final String COL9 = "saturday";
    private static final String COL10 = "sunday";
    private static final String COL11 = "state";
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
        sqLiteDatabase = context.openOrCreateDatabase(TABLE_NAME, Context.MODE_PRIVATE, null);
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL1 + " TEXT, " + COL2 + " INT, "+ COL3 + " INT, "+ COL4 + " INT, "+ COL5 + " INT, " +
                COL6 + " INT, " + COL7 + " INT, " + COL8 + " INT, " + COL9 + " INT, " + COL10 + " INT, " + COL11 + " INT)";
        //sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addData(String name, int hour, int minutes, ArrayList<Integer> recurrence, int state){

        SQLiteDatabase db = this.getWritableDatabase();

        String sql;
        Cursor cursor = db.rawQuery("SELECT "+COL1+" FROM " + TABLE_NAME + " WHERE " + COL1 + " =?", new String[]{name});

        if(cursor.getCount() > 0)
            sql = "UPDATE " + TABLE_NAME + " SET " +COL1+ " = '" + name + "', " +COL2+ " = "  + hour + ", " +COL3+ " = " +
                    minutes + ", " +COL4+ " = " + recurrence.get(0) + ", " +COL5+ " = " + recurrence.get(1) + ", " +COL6+ " = " + recurrence.get(2) + ", " +COL7+ " = "  + recurrence.get(3)
                    + ", " +COL8+ " = " + recurrence.get(4) + ", " +COL9+ " = " + recurrence.get(5) + ", " +COL10+ " = " + recurrence.get(6) + ", "  +COL11+ " = " + state + " WHERE " +COL1+ " = '"+name+"'";
        else
            sql = "INSERT or replace INTO " + TABLE_NAME + " VALUES ('" + name + "', " + hour + ", "+
                minutes + ", " + recurrence.get(0) + ", " + recurrence.get(1) + ", " + recurrence.get(2) + ", " + recurrence.get(3)
                + ", " + recurrence.get(4) + ", " + recurrence.get(5) + ", " + recurrence.get(6) + ", " + state + " )";

        db.execSQL(sql);

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT "+COL1+ ", " +COL2+ ", " +COL3+ ", "
                +COL4+ ", " +COL5+ ", " +COL6+ ", " +COL7+ ", " +COL8+ ", " +COL9+ ", " +COL10+ ", " +COL11+ " FROM "+ TABLE_NAME , null);

        return data;
    }

    public void deleteItem(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1 + "=?", new String[]{name});
    }
}
