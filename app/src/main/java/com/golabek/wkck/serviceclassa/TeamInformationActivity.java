package com.golabek.wkck.serviceclassa;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.golabek.wkck.serviceclassa.database.forQueries.TeamsToTable;
import com.golabek.wkck.serviceclassa.database.models.Teams;
import com.golabek.wkck.serviceclassa.database.operations.TableWithResults;
import com.golabek.wkck.serviceclassa.database.operations.TeamsController;

public class TeamInformationActivity extends AppCompatActivity {
    public static Integer id;
    public static boolean returnToTable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_information);

        Button button = findViewById(R.id.return_button_team_information);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if(returnToTable) {
                    startActivity(new Intent(TeamInformationActivity.this, Group1Activity.class));
                }
                else {
                    startActivity(new Intent(TeamInformationActivity.this, OtherActivity.class));
                }
                finishAfterTransition();
            }
        });
        fillView(button);

    }
    private void fillView(Button button){
        TableWithResults tableWithResults = new TableWithResults(getApplicationContext());
        TeamsToTable teamsToTable = tableWithResults.getTeamInfoAboutSeason(id, 1);
        TeamsController teamsController = new TeamsController(getApplicationContext());
        Teams teams = teamsController.getTeamById(id);
        setDescription(teams, teamsToTable);
    }

    private void setDescription(Teams teams, TeamsToTable teamsToTable){
        TextView textView = (TextView) findViewById(R.id.name_of_team_TextView);
        textView.setText(teams.getName());
        textView = (TextView) findViewById(R.id.name_of_group);
        textView.setText(teams.getNameOfGroup());
        textView = (TextView) findViewById(R.id.playedMatchesTeam);
        String tmpText = textView.getText().toString()+teamsToTable.getMatchesPlayed();
        textView.setText(tmpText);
        textView = (TextView) findViewById(R.id.pointsTeam);
        tmpText = textView.getText().toString()+teamsToTable.getScoredGoals().toString();
        textView.setText(tmpText);
        textView = (TextView) findViewById(R.id.ScoredGoalsTeam);
        tmpText = textView.getText().toString()+teamsToTable.getScoredGoals().toString();
        textView.setText(tmpText);
        textView = (TextView) findViewById(R.id.LostGoalsTeam);
        tmpText = textView.getText().toString()+teamsToTable.getLostGoals().toString();
        textView.setText(tmpText);

    }
}
