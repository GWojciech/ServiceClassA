package com.golabek.wkck.serviceclassa.database.operations;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.golabek.wkck.serviceclassa.database.DBHelper;
import com.golabek.wkck.serviceclassa.database.forQueries.TeamsToTable;
import java.util.ArrayList;
import java.util.List;

public class TableWithResults {

    private Context context;
    public static int idTeamToShow;

    public TableWithResults(Context context) {
        this.context = context;

    }

    public List<TeamsToTable> getTeamsWithGroups(int order, int numberOfGroup, String orderBy) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<TeamsToTable> listOfTeamsWithGroup = new ArrayList<>();
        Cursor cursor = null;
        if (order == 1) {
            cursor = db.rawQuery("SELECT TEAMS.id as idT," +
                            " TEAMS.name as team," +
                            "SEASONS.MATCHES_PLAYED as mpl, SEASONS.POINTS as poi,  SEASONS.scored_goals as scgo, " +
                            "SEASONS.LOST_GOALS as logo " +
                            "FROM TEAMS, SEASONS " +
                            "WHERE TEAMS.GROUP_ID=" + numberOfGroup +
                            " AND SEASONS.TEAM_ID=TEAMS.ID " +
                            "AND SEASONS.season_name = '2017/2018' " +
                            "ORDER BY "+ orderBy+ " DESC"
                    , null);
        } else {
            cursor = db.rawQuery("SELECT TEAMS.id as idT," +
                            " TEAMS.NAME as team," +
                            "SEASONS.MATCHES_PLAYED as mpl, SEASONS.POINTS as poi,  SEASONS.scored_goals as scgo, " +
                            "SEASONS.LOST_GOALS as logo " +
                            "FROM TEAMS, SEASONS " +
                            "WHERE TEAMS.GROUP_ID=" + numberOfGroup +
                            " AND SEASONS.TEAM_ID=TEAMS.ID " +
                            "AND SEASONS.season_name = '2017/2018' " +
                            "ORDER BY "+ orderBy+ " ASC"
                    , null);
        }
        while (cursor.moveToNext()) {
            TeamsToTable teamsToTable = new TeamsToTable();
            teamsToTable.setTeamId(cursor.getInt(cursor.getColumnIndexOrThrow("idT")));
            teamsToTable.setTeamName(cursor.getString(cursor.getColumnIndexOrThrow("team")));
            teamsToTable.setMatchesPlayed(cursor.getInt(cursor.getColumnIndexOrThrow("mpl")));
            teamsToTable.setPoints(cursor.getInt(cursor.getColumnIndex("poi")));
            teamsToTable.setScoredGoals(cursor.getInt(cursor.getColumnIndex("scgo")));
            teamsToTable.setLostGoals(cursor.getInt(cursor.getColumnIndex("logo")));

            listOfTeamsWithGroup.add(teamsToTable);
        }
        cursor.close();
        dbHelper.close();
        return listOfTeamsWithGroup;
    }

    public TeamsToTable getTeamInfoAboutSeason(int teamId, int numberOfGroup) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT TEAMS.id as idT," +
                        " TEAMS.name as team," +
                        "SEASONS.MATCHES_PLAYED as mpl, SEASONS.POINTS as poi,  SEASONS.scored_goals as scgo, " +
                        "SEASONS.LOST_GOALS as logo " +
                        "FROM TEAMS, SEASONS " +
                        "WHERE TEAMS.GROUP_ID=" + numberOfGroup +
                        " AND SEASONS.TEAM_ID=TEAMS.ID " +
                        "AND SEASONS.season_name = '2017/2018'" +
                        "AND TEAMS.ID=" + teamId +
                        " ORDER BY POINTS DESC"
                , null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        TeamsToTable teamsToTable = new TeamsToTable();
        teamsToTable.setTeamId(cursor.getInt(cursor.getColumnIndexOrThrow("idT")));
        teamsToTable.setTeamName(cursor.getString(cursor.getColumnIndexOrThrow("team")));
        teamsToTable.setMatchesPlayed(cursor.getInt(cursor.getColumnIndexOrThrow("mpl")));
        teamsToTable.setPoints(cursor.getInt(cursor.getColumnIndex("poi")));
        teamsToTable.setScoredGoals(cursor.getInt(cursor.getColumnIndex("scgo")));
        teamsToTable.setLostGoals(cursor.getInt(cursor.getColumnIndex("logo")));
        cursor.close();
        dbHelper.close();
        return teamsToTable;


    }
}
