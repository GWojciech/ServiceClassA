package com.golabek.wkck.serviceclassa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.database.models.mock.Matches;

public class MatchesInformation extends AppCompatActivity {
    public static Matches matchToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches_information);
        showInfo();
    }

    private void fillNameAndResults(){
        TextView textView = (TextView) findViewById(R.id.homeTeamNameInfoTextView);
        textView.setText(matchToShow.getHomeTeam());
        textView = (TextView) findViewById(R.id.awayTeamNameInfoTextView);
        textView.setText(matchToShow.getAwayTeam());
        textView = (TextView) findViewById(R.id.homeTeamGoalsInfo);
        textView.setText(matchToShow.getHomeTeamGoals().toString());
        textView = (TextView) findViewById(R.id.awayTeamGoalsInfo);
        textView.setText(matchToShow.getAwayTeamGoals().toString());
        textView = (TextView) findViewById(R.id.matchEventsTextView);
        textView.setText("Przebieg spotkania:");

    }

    private void showInfo(){
        fillNameAndResults();
        int numberOfIterations = matchToShow.getHomeTeamGoals();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutToInfo);
        TextView textView = null;
        Integer goal= null, minutes= 10;
        for(int i=0; i<numberOfIterations; i++){
            textView = new TextView(getApplicationContext());
            goal = i+1;
            textView.setText(goal.toString()+":0"+" Kowalski "+minutes.toString()+"'");
            minutes+=10;
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setTextColor(Color.BLACK);
            linearLayout.addView(textView);

        }
        goal=0;
        numberOfIterations = matchToShow.getAwayTeamGoals();
        for(int i=0; i<numberOfIterations; i++){
            textView = new TextView(getApplicationContext());
            goal = i+1;
            textView.setText(matchToShow.getHomeTeamGoals()+":"+goal.toString() +" Kowalski "+minutes.toString()+"'");
            minutes+=10;
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setTextColor(Color.BLACK);
            linearLayout.addView(textView);

        }
        Button button = (Button) findViewById(R.id.returnToResultsFromInfo);
        //linearLayout.addView(button);
        initListener(button);
        textView =(TextView)findViewById(R.id.moreMatchesHyperLinkTextView);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://kieleckapilka.pl/competition.php?compid=806'> Więcej informacji </a>";
        textView.setText(Html.fromHtml(text));
    }

    private void initListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MatchesInformation.this, Group1Activity.class));
                finishAfterTransition();
            }
        });
    }
}
