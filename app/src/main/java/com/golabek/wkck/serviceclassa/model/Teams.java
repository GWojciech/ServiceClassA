package com.golabek.wkck.serviceclassa.model;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Odbiorca on 2018-03-22.
 */

public class Teams {

    private Integer id;
    private String name;
    private Integer group_id;
    private String nameOfGroup;

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

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
    }

    public static final String TABLE_NAME = "teams";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_GROUP_ID = "group_id";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Teams.TABLE_NAME + " (" +
                    Teams.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    Teams.COLUMN_NAME_NAME + " TEXT," +
                    Teams.COLUMN_NAME_GROUP_ID + " INTEGER, " +
                    "FOREIGN KEY("+Teams.COLUMN_NAME_GROUP_ID+") REFERENCES "+ Groups.TABLE_NAME
                    +"("+Groups.COLUMN_NAME_ID+"))";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Teams.TABLE_NAME;


    @Override
    public String toString(){
        return this.name+ " | " + this.nameOfGroup+"\n";
    }

    public static void insertAll(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TEAMS VALUES(1,'Astra Piekoszów', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(2,'Tęcza Gowarczów', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(3,'ŁKS Łopuszno', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(4,'Sokół-Nordkalk Górnik Rykoszyn', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(5,'Orlęta Kielce', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(6,'Politechnika Świętokrzyska Kielce', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(7,'Wicher Miedziana Góra', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(8,'Radiator$ Stąporków', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(9,'Top-Spin Promnik', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(10,'Victoria Mniów', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(11,'MSS-Klonówka Masłów', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(12,'Piast Chęciny', 1)");
    }


}
