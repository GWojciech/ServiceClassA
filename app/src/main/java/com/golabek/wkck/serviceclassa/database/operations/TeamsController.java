package com.golabek.wkck.serviceclassa.database.operations;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.golabek.wkck.serviceclassa.database.DBHelper;
import com.golabek.wkck.serviceclassa.database.models.Groups;
import com.golabek.wkck.serviceclassa.database.models.Teams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Odbiorca on 2018-03-22.
 */

public class TeamsController {


    private Context context;
    private String[] projection = {
            Groups.COLUMN_NAME_ID,
            Groups.COLUMN_NAME_NAME
    };

    public TeamsController(Context context){
        this.context=context;

    }


    //db.execSQL("DROP TABLE IF EXISTS " + Groups.TABLE_NAME);;
// Define a projection that specifies which columns from the database
// you will actually use after this query.


/*
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
*/
    public List <Teams> getTeamsWithGroups(){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List <Teams> listOfTeamsWithGroup = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT TEAMS.NAME as team, GROUPS.NAME as gro, SEASONS.POINTS as poi FROM GROUPS, TEAMS, SEASONS WHERE GROUPS.ID= TEAMS.GROUP_ID AND SEASONS.TEAM_ID=TEAMS.ID", null);
        while (cursor.moveToNext()) {
            Teams teams = new Teams();
            teams.setName(cursor.getString(cursor.getColumnIndexOrThrow("team")));
            teams.setNameOfGroup(cursor.getString(cursor.getColumnIndexOrThrow("gro")));
            teams.setPoints(cursor.getInt(cursor.getColumnIndex("poi")));
            listOfTeamsWithGroup.add(teams);
        }
        cursor.close();
        dbHelper.close();
        return listOfTeamsWithGroup;
    }

}
