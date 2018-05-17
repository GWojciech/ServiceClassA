package com.golabek.wkck.serviceclassa;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.database.forQueries.mock.ScorersRank;
import com.golabek.wkck.serviceclassa.database.operations.mock.BestScorers;

import java.util.List;
import java.util.Random;

public class PlayerInformationActivity extends AppCompatActivity {
    public static Integer number;
    public static boolean returnToClassification = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_information);
        fillView();

        Button button = (Button) findViewById(R.id.buttonReturnPlayer);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if(returnToClassification) {
                    startActivity(new Intent(PlayerInformationActivity.this, Group1Activity.class));
                }
                else {
                    startActivity(new Intent(PlayerInformationActivity.this, OtherActivity.class));
                }
                finishAfterTransition();
            }
        });

    }

    private void fillView(){
        BestScorers bestScorers = new BestScorers();
        List<ScorersRank> listOfScorers = bestScorers.getScorersList();
        ScorersRank scorersRank = listOfScorers.get(number);
        TextView textView = (TextView) findViewById(R.id.nameOfPlayer);
        textView.setText(scorersRank.getName());
        textView = (TextView) findViewById(R.id.surnameOfPlayer);
        textView.setText(scorersRank.getSurname());
        textView = (TextView) findViewById(R.id.surnameOfPlayer);
        textView.setText(scorersRank.getSurname());
        textView = (TextView) findViewById(R.id.nameOfPlayersTeam);
        textView.setText(scorersRank.getTeamName());
        textView = (TextView) findViewById(R.id.goalsPlayer);
        textView.setText(textView.getText()+" "+scorersRank.getGoals().toString());
        textView = (TextView) findViewById(R.id.assistPlayer);
        textView.setText(textView.getText()+" "+ scorersRank.getAssists().toString());
        Random generator = new Random();
        textView = (TextView) findViewById(R.id.playedMatchesByPlayer);
        Integer number = 1+generator.nextInt(5);
        textView.setText(textView.getText()+" "+number.toString());

    }
}
