package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import static com.example.myapplication.DbConstants.*;
/**
 * Exercise 2.
 *
 * Implement this class' methods. Extend SQLiteOpenHelper to access database.
 */
public class Database {

    private DatabaseHelper carDbHelper;

    public Database(Context context) {
        this.carDbHelper = new DatabaseHelper(context);
    }

    public void addCar(Car car, Bitmap photoCaptureBitmap) {
        SQLiteDatabase db = carDbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_BRAND, car.getBrand());
            values.put(COLUMN_NAME_MODEL, car.getModel());
            values.put(COLUMN_NAME_ENGINE_SIZE, car.getEngineSize());
            values.put(COLUMN_NAME_AVERAGE_CONSUMPTION, car.getAverageConsumption());



            Bitmap newBitmap = Bitmap.createScaledBitmap(photoCaptureBitmap, 330,300, true);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            newBitmap.compress(Bitmap.CompressFormat.PNG, 5, bos);
            byte[] bArray = bos.toByteArray();
            values.put(COLUMN_NAME_PHOTO, bArray);

            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
    }

    public Car getCar(int position) {
        SQLiteDatabase db = carDbHelper.getReadableDatabase();

        try {
            String[] projection = { COLUMN_NAME_BRAND, COLUMN_NAME_MODEL, COLUMN_NAME_ENGINE_SIZE,COLUMN_NAME_AVERAGE_CONSUMPTION,COLUMN_NAME_PHOTO};

            String sortOrder = COLUMN_NAME_BRAND + " ASC";

            Cursor cursor = db.query(TABLE_NAME,  // The table to query
                    projection,                   // The columns to return
                    null,                         // The columns for the WHERE clause
                    null,                         // The values for the WHERE clause
                    null,                         // don't group the rows
                    null,                         // don't filter by row groups
                    sortOrder                     // The sort order
            );

            return getCarFromCursor(position, cursor);
        } finally {
            db.close();
        }
    }

    private Car getCarFromCursor(int position, Cursor cursor) {
        try {
            cursor.moveToPosition(position);

            String brand = cursor.getString(0);
            String model = cursor.getString(1);
            double engineSize = cursor.getDouble(2);
            double averageConsumption = cursor.getDouble(3);
            //int photoNumber = 1;
            byte[] image = cursor.getBlob(4);
            Bitmap bmp = BitmapFactory.decodeByteArray(image,0,image.length);



            cursor.close();

            return new Car(1,brand, model,engineSize,averageConsumption,bmp);
        } finally {
            cursor.close();
        }
    }

    public int getCarsNumber() {
        SQLiteDatabase db = carDbHelper.getReadableDatabase();

        try {
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, null, null);
        } finally {
            db.close();
        }
    }
}
