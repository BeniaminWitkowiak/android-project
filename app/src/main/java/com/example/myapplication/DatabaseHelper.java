package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import static com.example.myapplication.DbConstants.*;

/**
 * Created by ben_a_000 on 2016-05-19.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private DatabaseHelper DatabaseHelper;


    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 10;



    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_BRAND + " TEXT, "  +
                    COLUMN_NAME_MODEL + " TEXT, " +
                    COLUMN_NAME_ENGINE_SIZE + " REAL, "+
                    COLUMN_NAME_AVERAGE_CONSUMPTION + " REAL, "+
                    COLUMN_NAME_PHOTO + " BLOB);";


    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }





}
