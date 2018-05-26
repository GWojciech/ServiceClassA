package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.database.models.mock.Matches;
import com.golabek.wkck.serviceclassa.database.operations.mock.MatchdayScheudle;
import com.golabek.wkck.serviceclassa.helperforviews.ScheudleAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheudleGroup1Activity extends Fragment {
    private Integer matchday = 5;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scheudle_group1, container, false);
        initButtonListeners(view);
        fillListView(view);

        return  view;
    }

    private void initButtonListeners(View view) {

        final TextView textView = view.findViewById(R.id.scheudleTextView);
        Button button = (Button) view.findViewById(R.id.previousMatchdayScheudle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchday>5){
                    matchday--;
                    textView.setText(matchday.toString()+". kolejka");
                    fillListView(view.getRootView());
                }
            }
        });

        button = (Button) view.findViewById(R.id.nextMatchdayScheudle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchday<=7){
                    matchday++;
                    textView.setText(matchday.toString()+ ". kolejka");
                    fillListView(view.getRootView());
                }
            }
        });

    }

    private void fillListView(View view) {
        final View viewToThread = view;
        new Handler().post(new Runnable() {

            @Override
            public void run() {
                ListView listView = viewToThread.findViewById(R.id.listViewScheudle);
                MatchdayScheudle matchdayScheudle = new MatchdayScheudle(viewToThread.getContext());
                final List<Matches> matchesList = matchdayScheudle.getMatchdayScheudle(1);
                ScheudleAdapter scheudleAdapter = new ScheudleAdapter(viewToThread.getContext(), R.layout.scheudle_info, (ArrayList<Matches>) matchesList);
                listView.setAdapter(scheudleAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Calendar cal = Calendar.getInstance();
                        Matches match = matchesList.get(i);
                        Intent intent = new Intent(Intent.ACTION_EDIT);
                        intent.setType("vnd.android.cursor.item/event");
                        intent.putExtra("beginTime", match.getDateOfMatch().getTime());
                        intent.putExtra("allDay", false);
                        intent.putExtra("endTime", match.getDateOfMatch().getTime()+120*60*1000);
                        intent.putExtra("title", match.getHomeTeam()+" vs " + match.getAwayTeam());
                        startActivity(intent);
                    }
                });
            }
        });
    }

}
