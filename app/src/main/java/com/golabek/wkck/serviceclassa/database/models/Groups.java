package com.golabek.wkck.serviceclassa.database.models;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Odbiorca on 2018-03-22.
 */

public class Groups implements BaseColumns {

    private Integer id;
    private String name;

    public Groups() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/* Inner class that defines the table contents */

        public static final String TABLE_NAME = "groups";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Groups.TABLE_NAME + " (" +
                        Groups.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        Groups.COLUMN_NAME_NAME + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Groups.TABLE_NAME;

        public static void insertAll(SQLiteDatabase db){
            db.execSQL("INSERT INTO GROUPS VALUES (1,'Grupa pierwsza')");
            db.execSQL("INSERT INTO GROUPS VALUES (2,'Grupa druga')");
            db.execSQL("INSERT INTO GROUPS VALUES (3,'Grupa trzecia')");
        }

        @Override
        public String toString(){
            return this.id+ " | " + this.name+"\n";
        }
}
