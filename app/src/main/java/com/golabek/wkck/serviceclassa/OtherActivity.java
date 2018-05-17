package com.golabek.wkck.serviceclassa;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.golabek.wkck.serviceclassa.database.forQueries.TeamsToTable;
import com.golabek.wkck.serviceclassa.database.forQueries.mock.ScorersRank;
import com.golabek.wkck.serviceclassa.database.models.Teams;
import com.golabek.wkck.serviceclassa.database.operations.TableWithResults;
import com.golabek.wkck.serviceclassa.database.operations.mock.BestScorers;
import com.golabek.wkck.serviceclassa.helperforviews.SearchPlayerAdapter;
import com.golabek.wkck.serviceclassa.helperforviews.SearchTeamAdapter;

import java.util.ArrayList;
import java.util.List;


public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        initListener();



    }

    private void initListener(){
        Button button = (Button) findViewById(R.id.filterSelectButton);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioSearch);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId>0) {
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    String selectedName = radioButton.getText().toString();
                    switch(selectedName){
                        case "Zawodnik":
                            displayListOfPlayers();
                            break;

                        case "Drużyna":
                            displayListOfTeams();
                            break;
                    }
                }

            }
        });
    }

    private void displayListOfPlayers(){
        BestScorers bestScorers = new BestScorers();
        final List <ScorersRank> playersList = bestScorers.getScorersList();
        final SearchPlayerAdapter searchPlayerAdapter = new SearchPlayerAdapter(getApplicationContext(), R.layout.search_info, (ArrayList<ScorersRank>) playersList);
        ListView listView = (ListView) findViewById(R.id.listViewOther);
        listView.setAdapter(searchPlayerAdapter);
        EditText myFilter = (EditText) findViewById(R.id.editTextForSearchOther);
        listView.setTextFilterEnabled(true);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchPlayerAdapter.getFilter().filter(s.toString());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PlayerInformationActivity.number=i;
                PlayerInformationActivity.returnToClassification = false;

                startActivity(new Intent(OtherActivity.this, PlayerInformationActivity.class));
                finishAfterTransition();
            }
        });

    }

    private void displayListOfTeams(){
        TableWithResults tableWithResults = new TableWithResults(getApplicationContext());
        final List<TeamsToTable> teamsList  = tableWithResults.getTeamsWithGroups(1,1, "TEAMS.NAME");
        final SearchTeamAdapter searchTeamAdapter = new SearchTeamAdapter(this, R.layout.search_info, (ArrayList<TeamsToTable>) teamsList);
        ListView listView = (ListView) findViewById(R.id.listViewOther);
        listView.setAdapter(searchTeamAdapter);
        EditText myFilter = (EditText) findViewById(R.id.editTextForSearchOther);
        listView.setTextFilterEnabled(true);

        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchTeamAdapter.getFilter().filter(s.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = teamsList.get(i).getTeamId();
                TeamInformationActivity.id = id;
                TeamInformationActivity.returnToTable=false;
                startActivity(new Intent(OtherActivity.this, TeamInformationActivity.class));
                finishAfterTransition();
            }
        });

    }
}
