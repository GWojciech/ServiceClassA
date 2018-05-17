package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.golabek.wkck.serviceclassa.Group1Activity;
import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.TeamInformationActivity;
import com.golabek.wkck.serviceclassa.database.forQueries.TeamsToTable;
import com.golabek.wkck.serviceclassa.database.operations.TableWithResults;

import java.util.List;


public class TableGroup1Activity extends Fragment {

    private static int order=1;
    private static String orderBy="POINTS";
    public static int idTeamToShow;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_group1, container, false);
        setTeamsInTheView(view);
        initListeners(view);

        return view;
    }

    public void createSeasonTable(View view, int order, int numberOfGroup ){
        Integer pos = 0;
        TableWithResults tableWithResults = new TableWithResults(getContext());
        final List<TeamsToTable> listTable = tableWithResults.getTeamsWithGroups(order,1, orderBy);
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tableLayout1);
        TeamsToTable teamsToTable = null;
        TableRow newTableRow;
        for(Integer i=0; i<12; i++){
            pos++;
            teamsToTable = listTable.get(i);
            newTableRow = new TableRow(getContext());
            newTableRow.setMinimumHeight(50);
            if(i==0){
                newTableRow.setBackgroundColor(Color.GREEN);
            }
            else if(i==10 || i==11){
                newTableRow.setBackgroundColor(Color.RED);
            }
            TextView textView = new TextView(getContext());
            textView.setText(pos.toString());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setText(teamsToTable.getTeamName());
            textView.setTextSize(12f);
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(teamsToTable.getMatchesPlayed().toString());
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(teamsToTable.getPoints().toString());
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(teamsToTable.getScoredGoals().toString());
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(teamsToTable.getLostGoals().toString());
            final Integer finalI = i;
            newTableRow.setOnClickListener(new View.OnClickListener() {
                TeamsToTable teamsToTable;
                @Override
                public void onClick(View view) {
                    teamsToTable = listTable.get(finalI);
                    TeamInformationActivity.id = teamsToTable.getTeamId();
                    TeamInformationActivity.returnToTable=true;
                    Intent i = new Intent(getActivity(), TeamInformationActivity.class);
                    startActivity(i);
//                    ((Activity) getActivity()).overridePendingTransition(0,0);
                }
            });
            newTableRow.addView(textView);
            tableLayout.addView(newTableRow);

        }
    }



    public void setTeamsInTheView(View view) {
        createSeasonTable(view, order, 1);

    }


    private void initListeners(View view){
        Button button = (Button) view.findViewById(R.id.buttonPoints);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableWithResults tableWithResults = new TableWithResults(getContext());
                TableLayout table = (TableLayout) view.findViewById(R.id.tableLayout1);
                if(order==1){
                    order--;
                }
                else {
                    order++;
                }
                orderBy="POINTS";
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        TableLayout tableLayout = getView().findViewById(R.id.tableLayout1);
                        tableLayout.removeViewsInLayout(1,12);
                        setTeamsInTheView(getView());
                    }
                });

            }
        });

        button = (Button) view.findViewById(R.id.buttonTeam);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableWithResults tableWithResults = new TableWithResults(getContext());
                TableLayout table = (TableLayout) view.findViewById(R.id.tableLayout1);
                if(order==1){
                    order--;
                }
                else {
                    order++;
                }
                orderBy="TEAMS.NAME";
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        TableLayout tableLayout = getView().findViewById(R.id.tableLayout1);
                        tableLayout.removeViewsInLayout(1,12);
                        setTeamsInTheView(getView());
                    }
                });

            }
        });

        button = (Button) view.findViewById(R.id.buttonMatches);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableWithResults tableWithResults = new TableWithResults(getContext());
                TableLayout table = (TableLayout) view.findViewById(R.id.tableLayout1);
                if(order==1){
                    order--;
                }
                else {
                    order++;
                }
                orderBy="SEASONS.MATCHES_PLAYED";
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        TableLayout tableLayout = getView().findViewById(R.id.tableLayout1);
                        tableLayout.removeViewsInLayout(1,12);
                        setTeamsInTheView(getView());
                    }
                });

            }
        });

        button = (Button) view.findViewById(R.id.buttonScoredGoals);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableWithResults tableWithResults = new TableWithResults(getContext());
                TableLayout table = (TableLayout) view.findViewById(R.id.tableLayout1);
                if(order==1){
                    order--;
                }
                else {
                    order++;
                }
                orderBy="SEASONS.SCORED_GOALS";
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        TableLayout tableLayout = getView().findViewById(R.id.tableLayout1);
                        tableLayout.removeViewsInLayout(1,12);
                        setTeamsInTheView(getView());
                    }
                });

            }
        });

        button = (Button) view.findViewById(R.id.buttonLostGoals);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableWithResults tableWithResults = new TableWithResults(getContext());
                TableLayout table = (TableLayout) view.findViewById(R.id.tableLayout1);
                if(order==1){
                    order--;
                }
                else {
                    order++;
                }
                orderBy="SEASONS.LOST_GOALS";
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        TableLayout tableLayout = getView().findViewById(R.id.tableLayout1);
                        tableLayout.removeViewsInLayout(1,12);
                        setTeamsInTheView(getView());
                    }
                });

            }
        });
    }


}
