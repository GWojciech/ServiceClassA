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

import com.golabek.wkck.serviceclassa.PlayerInformationActivity;
import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.database.forQueries.mock.ScorersRank;
import com.golabek.wkck.serviceclassa.database.operations.TableWithResults;
import com.golabek.wkck.serviceclassa.database.operations.mock.BestScorers;

import java.util.List;


public class ClassificationsGroup1Activity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classifications_group1, container, false);
        createScorersTable(view);
        initListeners(view);

        return  view;
    }

    private void initListeners(View view) {
        Button button =  (Button) view.findViewById(R.id.GoalsOfScorer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableWithResults tableWithResults = new TableWithResults(getContext());
                TableLayout table = (TableLayout) view.findViewById(R.id.tabClassGr1);
                if(ScorersRank.orderHelp!=1){
                    ScorersRank.orderHelp=1;
                }
                else {
                    ScorersRank.orderHelp=2;
                }
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        TableLayout tableLayout = getView().findViewById(R.id.tabClassGr1);
                        tableLayout.removeViewsInLayout(1,12);
                        createScorersTable(getView());
                    }
                });
            }
        });

        button =  (Button) view.findViewById(R.id.AssistOfScorer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableWithResults tableWithResults = new TableWithResults(getContext());
                TableLayout table = (TableLayout) view.findViewById(R.id.tabClassGr1);
                if(ScorersRank.orderHelp!=3){
                    ScorersRank.orderHelp=3;
                }
                else {
                    ScorersRank.orderHelp=4;
                }
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        TableLayout tableLayout = getView().findViewById(R.id.tabClassGr1);
                        tableLayout.removeViewsInLayout(1,12);
                        createScorersTable(getView());
                    }
                });
            }
        });

    }

    public void createScorersTable(View view ){
        Integer pos = 0;
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tabClassGr1);
        BestScorers bestScorers = new BestScorers();
        ScorersRank scorersRank = null;
        TableRow newTableRow;
        final List<ScorersRank> listOfScorers = bestScorers.getScorersList();
        for(Integer i=0; i<12; i++){
            pos++;
            scorersRank = listOfScorers.get(i);
            newTableRow = new TableRow(getContext());
            newTableRow.setMinimumHeight(50);
            if(i==0){
                newTableRow.setBackgroundColor(Color.GREEN);
            }
            TextView textView = new TextView(getContext());
            textView.setText(pos.toString());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setText(scorersRank.getName());
            textView.setTextSize(12f);
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setTextSize(12f);
            textView.setText(scorersRank.getSurname());
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setTextSize(10f);
            textView.setText(scorersRank.getTeamName());
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(scorersRank.getGoals().toString());
            newTableRow.addView(textView);
            textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(scorersRank.getAssists().toString());
            final Integer finalI = i;
            newTableRow.setOnClickListener(new View.OnClickListener() {
                ScorersRank scorersRank;
                @Override
                public void onClick(View view) {
                    scorersRank = listOfScorers.get(finalI);
                    PlayerInformationActivity.number=finalI;
                    PlayerInformationActivity.returnToClassification = true;
                    Intent i = new Intent(getActivity(), PlayerInformationActivity.class);
                    startActivity(i);
//                    ((Activity) getActivity()).overridePendingTransition(0,0);



                }
            });
            newTableRow.addView(textView);
            tableLayout.addView(newTableRow);

        }
    }
}
