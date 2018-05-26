package com.golabek.wkck.serviceclassa.database.models;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Odbiorca on 2018-03-22.
 */

public class Teams {

    private Integer id;
    private String name;
    private Integer group_id;
    private String website;
    private String nameOfGroup;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    private Integer points;

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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
    public static final String COLUMN_NAME_WEBSITE = "website";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Teams.TABLE_NAME + " (" +
                    Teams.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    Teams.COLUMN_NAME_NAME + " TEXT," +
                    Teams.COLUMN_NAME_WEBSITE + " TEXT,"+
                    Teams.COLUMN_NAME_GROUP_ID + " INTEGER, " +
                    "FOREIGN KEY("+Teams.COLUMN_NAME_GROUP_ID+") REFERENCES "+ Groups.TABLE_NAME
                    +"("+Groups.COLUMN_NAME_ID+"))";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Teams.TABLE_NAME;


    @Override
    public String toString(){
        return this.name+ " | " + this.nameOfGroup+ " | "+ this.points + "\n";
    }

    public static void insertAll(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TEAMS VALUES(1,'Astra Piekoszów', 'https://www.facebook.com/KSAstraPiekoszow/',1)");
        db.execSQL("INSERT INTO TEAMS VALUES(2,'Tęcza Gowarczów','https://www.facebook.com/TeczaGowarczow/', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(3,'ŁKS Łopuszno','https://www.facebook.com/LopuszanskiKlubSportowy/', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(4,'Sokół-Nordkalk Górnik Rykoszyn','https://www.facebook.com/SokolRykoszyn/', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(5,'Orlęta Kielce','http://www.orletakielce.pl/', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(6,'Politechnika Świętokrzyska Kielce','https://www.facebook.com/AzsPSkKielce/' ,1)");
        db.execSQL("INSERT INTO TEAMS VALUES(7,'Wicher Miedziana Góra','http://wichermiedzianagora.futbolowo.pl/', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(8,'Radiator$ Stąporków','https://www.facebook.com/KS-Radiator-St%C4%85pork%C3%B3w-1083786644970189/' ,1)");
        db.execSQL("INSERT INTO TEAMS VALUES(9,'Top-Spin Promnik','https://www.facebook.com/topspinpromnik/', 1)");
        db.execSQL("INSERT INTO TEAMS VALUES(10,'Victoria Mniów','http://victoriamniow.futbolowo.pl/' ,1)");
        db.execSQL("INSERT INTO TEAMS VALUES(11,'MSS-Klonówka Masłów','http://klonowkamaslow.futbolowo.pl/' ,1)");
        db.execSQL("INSERT INTO TEAMS VALUES(12,'Piast Chęciny','https://www.facebook.com/kspiastcheciny', 1)");
    }


}
