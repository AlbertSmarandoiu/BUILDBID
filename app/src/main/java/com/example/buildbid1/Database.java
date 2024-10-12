package com.example.buildbid1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating the 'user' table
        String qry1 = "CREATE TABLE user (username TEXT, email TEXT, password TEXT)";
        db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // You can handle database upgrades here, if necessary.
    }

    // Method to register a new user
    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase db = getWritableDatabase();
        // Insert into 'user' table, not 'users'
        db.insert("user", null, cv);
        db.close();
    }

    // Method to login a user by checking credentials
    public int login(String username, String password) {
        int result = 0;
        String[] str = {username, password};

        SQLiteDatabase db = getReadableDatabase();
        // Query the 'user' table (not 'users')
        Cursor c = db.rawQuery("SELECT * FROM user WHERE username=? AND password=?", str);
        if (c.moveToFirst()) {
            result = 1; // login successful
        }
        c.close(); // Close the cursor to avoid resource leaks
        db.close(); // Close the database
        return result;
    }
}
