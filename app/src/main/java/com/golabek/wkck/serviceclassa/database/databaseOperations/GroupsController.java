package com.golabek.wkck.serviceclassa.database.databaseOperations;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.golabek.wkck.serviceclassa.database.DBHelper;
import com.golabek.wkck.serviceclassa.database.databaseModels.Groups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Odbiorca on 2018-03-22.
 */

public class GroupsController{


    private Context context;
    private String[] projection = {
            Groups.COLUMN_NAME_ID,
            Groups.COLUMN_NAME_NAME
    };

    public GroupsController(Context context){
        this.context=context;

    }


    //db.execSQL("DROP TABLE IF EXISTS " + Groups.TABLE_NAME);;
// Define a projection that specifies which columns from the database
// you will actually use after this query.



    public List<Groups> getALlGroups() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<Groups> itemIds = new ArrayList<>();
        Cursor cursor = db.query(
                Groups.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        while (cursor.moveToNext()) {
            Groups gr = new Groups();

            gr.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Groups.COLUMN_NAME_ID)));
            gr.setName(cursor.getString(cursor.getColumnIndexOrThrow(Groups.COLUMN_NAME_NAME)));
            itemIds.add(gr);
        }
        cursor.close();
        dbHelper.close();
        return itemIds;
    }
}