package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.golabek.wkck.serviceclassa.MatchsInformation;
import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.database.forQueries.mock.MatchesForResults;
import com.golabek.wkck.serviceclassa.database.models.mock.Matches;
import com.golabek.wkck.serviceclassa.helperforviews.ResultsAdapter;


import java.util.ArrayList;
import java.util.List;

public class ResultsGroup1Activity extends Fragment {
    private Integer matchday = 4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_group1, container, false);
        initButtonListeners(view);
        fillListView(view);

        return  view;
    }

    private void fillListView(View view){
        final View viewToThread = view;
        new Handler().post(new Runnable() {

            @Override
            public void run()
            {
                ListView listView = viewToThread.findViewById(R.id.listViewResultsGroup1);
                MatchesForResults matchesForResults = new MatchesForResults(viewToThread.getContext());
                final List<Matches> matchesList = matchesForResults.drawResults();
                ResultsAdapter resultsAdapter = new ResultsAdapter(viewToThread.getContext(), R.layout.result_info, (ArrayList<Matches>) matchesList);
                listView.setAdapter(resultsAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        MatchsInformation.matchToShow = matchesList.get(i);
                        Log.d("UWAGA!", matchesList.get(i).toString());
                        startActivity(new Intent(getActivity(), MatchsInformation.class));
                        getActivity().finishAfterTransition();
                    }
                });
            }
        });

    }

    private void initButtonListeners(View view) {

        final TextView textView = view.findViewById(R.id.resultsTextView);
        Button button = (Button) view.findViewById(R.id.previousMatchdayResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchday>1){
                    matchday--;
                    textView.setText(matchday.toString()+". kolejka");
                    fillListView(view.getRootView());
                }
            }
        });

        button = (Button) view.findViewById(R.id.nextMatchdayResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchday<4){
                    matchday++;
                    textView.setText(matchday.toString()+ ". kolejka");
                    fillListView(view.getRootView());
                }
            }
        });

    }
}
