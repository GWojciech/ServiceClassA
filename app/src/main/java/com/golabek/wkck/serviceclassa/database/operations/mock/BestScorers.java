package com.golabek.wkck.serviceclassa.database.operations.mock;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.database.DBHelper;
import com.golabek.wkck.serviceclassa.database.forQueries.TeamsToTable;
import com.golabek.wkck.serviceclassa.database.forQueries.mock.ScorersRank;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestScorers {

    private Context context;

    public BestScorers (Context context){
        this.context=context;

    }

    private String [] names = {"Jan", "Mateusz", "Wojciech", "Piotr", "Marian", "Paweł", "Ignacy", "Walerian","Krzysztof", "Andrzej", "Bartłomiej", "Szymon"};
    private String [] surnames = {"Kowalski", "Jansda", "Skowronek", "Kowal", "Kot", "Igos", "Łatas", "Warki", "Mapkli", "Zizia", "Łozia", "Kici"};
    private String [] teamName ={"PŚk Kielce", "Orlęta Kielce", "MSS-Klonówka Masłów", "Astra Piekoszów", "Górnik Rykoszyn", "Wicher Miedziana Góra", "Radiator$ Stąporków", "Top-Spin Promnik", "Victoria Mniów" , "Piast Chęciny", "ŁKS Łopuszno", "Tęcza Gowarczów"};
    private Integer [] goals ={1,3,6,4,2,7,9,2,5,7,6,4,1};
    private Integer [] assists = {5,7,3,1,6,8,2,7,4,2,1,4};

    public List<ScorersRank> getScorersList (){

        List <ScorersRank> listOfScorers = new ArrayList<>();

        ScorersRank scorersRank;
        for(Integer i =0; i<12; i++){
            scorersRank = new ScorersRank();
            scorersRank.setName(names[i]);
            scorersRank.setSurname(surnames[i]);
            scorersRank.setTeamName(teamName[i]);
            scorersRank.setGoals(goals[i]);
            scorersRank.setAssists(assists[i]);
            listOfScorers.add(scorersRank);
        }
        Log.d("LISTA:", listOfScorers.toString());
        Collections.sort(listOfScorers);
        Log.d("LISTA:", listOfScorers.toString());
        return listOfScorers;
    }

    public void createScorersTable(View view ){
        Integer pos = 0;
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tabClassGr1);
        ScorersRank scorersRank = null;
        TableRow newTableRow;
        List <ScorersRank> listOfScorers = getScorersList();
        for(Integer i=0; i<12; i++){
            pos++;
            scorersRank = listOfScorers.get(i);
            newTableRow = new TableRow(context);
            if(i==0){
                newTableRow.setBackgroundColor(Color.GREEN);
            }
            TextView textView = new TextView(context);
            textView.setText(pos.toString());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setText(scorersRank.getName());
            textView.setTextSize(12f);
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setTextSize(12f);
            textView.setText(scorersRank.getSurname());
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setTextSize(10f);
            textView.setText(scorersRank.getTeamName());
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(scorersRank.getGoals().toString());
            newTableRow.addView(textView);
            textView = new TextView(context);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(scorersRank.getAssists().toString());
            newTableRow.addView(textView);
            tableLayout.addView(newTableRow);

        }
    }
}
