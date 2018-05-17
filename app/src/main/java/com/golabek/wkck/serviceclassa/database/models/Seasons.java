package com.golabek.wkck.serviceclassa.database.models;

import android.database.sqlite.SQLiteDatabase;

public class Seasons {
    private Integer id;
    private Integer teamId;
    private Integer matchesPlayed;
    private Integer Points;
    private Integer scoredGoals;
    private Integer lostGoals;
    private String seasonName;

    public static final String TABLE_NAME = "seasons";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEAM_ID = "team_id";
    public static final String COLUMN_MATCHES_PLAYED = "matches_played";
    public static final String COLUMN_POINTS= "points";
    public static final String COLUMN_SCORED_GOALS= "scored_goals";
    public static final String COLUMN_LOST_GOALS = "lost_goals";
    public static final String COLUMN_SEASON_NAME = "season_name";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Seasons.TABLE_NAME + " (" +
                    Seasons.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    Seasons.COLUMN_TEAM_ID + " INTEGER," +
                    Seasons.COLUMN_MATCHES_PLAYED + " INTEGER,"+
                    Seasons.COLUMN_POINTS + " INTEGER," +
                    Seasons.COLUMN_SCORED_GOALS + " INTEGER,"+
                    Seasons.COLUMN_LOST_GOALS + " INTEGER," +
                    Seasons.COLUMN_SEASON_NAME + " TEXT,"+
                    "FOREIGN KEY("+Seasons.COLUMN_TEAM_ID+") REFERENCES "+ Teams.TABLE_NAME+
                    " ("+Teams.COLUMN_NAME_ID+"))";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Seasons.TABLE_NAME;



    @Override
    public String toString(){
        return this.id+ " | " + this.COLUMN_SEASON_NAME+"\n";
    }

    public static void insertAll(SQLiteDatabase db) {
        db.execSQL("INSERT INTO seasons VALUES(1,1,5,3,13,4, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(2,2,5,4,18,10, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(3,3,5,2,4,12, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(4,4,5,12,20,6, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(5,5,5,15,10,1, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(6,6,5,8,5,23, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(7,7,5,10,30,24, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(8,8,5,4,14,5, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(9,9,5,4,9,2, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(10,10,5,3,3,12, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(11,11,5,3,15,24, '2017/2018')");
        db.execSQL("INSERT INTO seasons VALUES(12,12,5,3,16,24, '2017/2018')");

    }
}
