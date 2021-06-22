package com.example.a2_hamad_191071;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQlitedb_helper extends SQLiteOpenHelper {
    private Context mainContext;
    private static final String DATABASE_NAME="studentRecords.db";
    private static int DATABASE_VERSION=1;
    private static final String TABLE_NAME="studentInfo";
    private String Create_query="create table "+ TABLE_NAME + " (std_id TEXT primary key, " +
            "std_name TEXT, std_age TEXT, std_city TEXT)";

    public SQlitedb_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mainContext=context;
        Toast.makeText(context, "Database Created ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_query);
        Toast.makeText(mainContext, "Table Created", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertStudentData(String id,String name,String age, String city) {
        SQLiteDatabase sql_db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("std_id", id);
        contentValues.put("std_name", name);
        contentValues.put("std_age", age);
        contentValues.put("std_city", city);
        long insertcheck = sql_db.insert(TABLE_NAME, null, contentValues);
        if (insertcheck == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getStudentInfo() {
        SQLiteDatabase sql_db = this.getWritableDatabase();
        Cursor cursor = sql_db.rawQuery("Select * from " + TABLE_NAME, null);
        return cursor;
    }

}

