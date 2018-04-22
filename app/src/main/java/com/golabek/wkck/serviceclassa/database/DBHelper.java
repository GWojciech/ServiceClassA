package com.golabek.wkck.serviceclassa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.golabek.wkck.serviceclassa.database.models.Groups;
import com.golabek.wkck.serviceclassa.database.models.Seasons;
import com.golabek.wkck.serviceclassa.database.models.Teams;

/**
 * Created by Odbiorca on 2018-03-22.
 */

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Groups.SQL_CREATE_ENTRIES);
        Groups.insertAll(db);
        db.execSQL(Teams.SQL_CREATE_ENTRIES);
        Teams.insertAll(db);
        db.execSQL(Seasons.SQL_CREATE_ENTRIES);
        Seasons.insertAll(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Seasons.SQL_DELETE_ENTRIES);
        db.execSQL(Teams.SQL_DELETE_ENTRIES);
        db.execSQL(Groups.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}