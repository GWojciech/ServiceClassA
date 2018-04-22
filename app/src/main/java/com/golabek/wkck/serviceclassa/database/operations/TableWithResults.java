package com.golabek.wkck.serviceclassa.database.operations;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.database.DBHelper;
import com.golabek.wkck.serviceclassa.database.forQueries.TeamsToTable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TableWithResults {

    private Context context;

    public TableWithResults (Context context){
        this.context=context;

    }
    public List<TeamsToTable> getTeamsWithGroups(int order, int numberOfGroup){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List <TeamsToTable> listOfTeamsWithGroup = new ArrayList<>();
        Cursor cursor = null;
        if(order==1) {
            cursor = db.rawQuery("SELECT TEAMS.name as team," +
                            "SEASONS.MATCHES_PLAYED as mpl, SEASONS.POINTS as poi,  SEASONS.scored_goals as scgo, " +
                            "SEASONS.LOST_GOALS as logo " +
                            "FROM TEAMS, SEASONS " +
                            "WHERE TEAMS.GROUP_ID="+ numberOfGroup +
                            " AND SEASONS.TEAM_ID=TEAMS.ID " +
                            "AND SEASONS.season_name = '2017/2018' " +
                            "ORDER BY POINTS DESC"
                    , null);
        }
        else{
            cursor = db.rawQuery("SELECT TEAMS.NAME as team," +
                            "SEASONS.MATCHES_PLAYED as mpl, SEASONS.POINTS as poi,  SEASONS.scored_goals as scgo, " +
                            "SEASONS.LOST_GOALS as logo " +
                            "FROM TEAMS, SEASONS " +
                            "WHERE TEAMS.GROUP_ID="+ numberOfGroup +
                            " AND SEASONS.TEAM_ID=TEAMS.ID " +
                            "AND SEASONS.season_name = '2017/2018' " +
                            "ORDER BY POINTS ASC"
                    , null);
        }
        while (cursor.moveToNext()) {
            TeamsToTable teamsToTable = new TeamsToTable();
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

    public void createSeasonTable(View view, int order, int numberOfGroup ){
        Integer pos = 0;
        List <TeamsToTable> listTable = getTeamsWithGroups(order,1);
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tableLayout1);
        TeamsToTable teamsToTable = null;
//        TextView posText = (TextView) view.findViewById(R.id.gr1Row1Pos);
//        posText.setText(pos.toString());
//        TextView teamText = (TextView) view.findViewById(R.id.gr1Row1Team);
//        teamText.setText(teamsToTable.getTeamName());
//        TextView matchesText = view.findViewById(R.id.gr1Row1Matches);
//        matchesText.setText(teamsToTable.getMatchesPlayed().toString());
//        TextView pointsText = view.findViewById(R.id.gr1Row1Points);
//        pointsText.setText(teamsToTable.getPoints().toString());
//        TextView scoredText = view.findViewById(R.id.gr1Row1Scored);
//        scoredText.setText(teamsToTable.getScoredGoals().toString());
//        TextView lostText = view.findViewById(R.id.gr1Row1Lost);
//        lostText.setText(teamsToTable.getLostGoals().toString());
        TableRow newTableRow;
        for(Integer i=0; i<12; i++){
            pos++;
            teamsToTable = listTable.get(i);
            newTableRow = new TableRow(context);
            if(i==0){
                newTableRow.setBackgroundColor(Color.GREEN);
            }
            else if(i==10 || i==11){
                newTableRow.setBackgroundColor(Color.RED);
            }
            TextView textView = new TextView(context);
            textView.setText(pos.toString());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setText(teamsToTable.getTeamName());
            textView.setTextSize(12f);
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(teamsToTable.getMatchesPlayed().toString());
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(teamsToTable.getPoints().toString());
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(teamsToTable.getScoredGoals().toString());
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(teamsToTable.getLostGoals().toString());
            newTableRow.addView(textView);
            tableLayout.addView(newTableRow);

        }
    }
}
