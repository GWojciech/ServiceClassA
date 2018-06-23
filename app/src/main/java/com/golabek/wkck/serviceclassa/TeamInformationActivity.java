package com.golabek.wkck.serviceclassa;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.database.models.ConcreteTeam;
import com.golabek.wkck.serviceclassa.services.InputStreamStringConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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
        new TeamTask().execute((Void) null);


    }

    private void setDescription(ConcreteTeam concreteTeam){
        String tmpText;
        TextView textView = (TextView) findViewById(R.id.name_of_team_TextView);
        textView.setText(concreteTeam.getTeamName());
        textView = findViewById(R.id.yearTeam);
        tmpText = textView.getText().toString()+concreteTeam.getYearOfEstablishment().toString();
        textView.setText(tmpText);
        textView = findViewById(R.id.websiteTeamTextView);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href="+concreteTeam.getWebsite()+"> Strona internetowa </a>";
        textView.setText(Html.fromHtml(text));
        textView = findViewById(R.id.theHighestLeague);
        tmpText = textView.getText().toString() + concreteTeam.getHighestLeague();
        textView.setText(tmpText);
        textView = (TextView) findViewById(R.id.name_of_group);
        textView.setText(concreteTeam.getNameOfGroup());
        textView = (TextView) findViewById(R.id.playedMatchesTeam);
        tmpText = textView.getText().toString()+concreteTeam.getPlayedMatches();
        textView.setText(tmpText);
        textView = (TextView) findViewById(R.id.pointsTeam);
        tmpText = textView.getText().toString() + concreteTeam.getPoints().toString();
        textView.setText(tmpText);
        textView = (TextView) findViewById(R.id.ScoredGoalsTeam);
        tmpText = textView.getText().toString()+ concreteTeam.getScoredGoals().toString();
        textView.setText(tmpText);
        textView = (TextView) findViewById(R.id.LostGoalsTeam);
        tmpText = textView.getText().toString()+ concreteTeam.getLostGoals().toString();
        textView.setText(tmpText);

    }

    public class TeamTask extends AsyncTask<Void, Void, Boolean> {

        private ConcreteTeam concreteTeam;

        JSONObject getTeamJSONObject(){
            URL url = null;
            try {
                url = new URL(getString(R.string.URL_address)+"/team/get/"+id.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(false);
                connection.setDoInput(true);
                JSONObject jsonObject;
                InputStream in = new BufferedInputStream(connection.getInputStream());
                String text = InputStreamStringConverter.streamToString(in);
                in.close();

                if(connection.getResponseCode()==200) {

                    jsonObject = new JSONObject(text);
                    connection.disconnect();
                    return jsonObject;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            JSONObject jsonObject = getTeamJSONObject();
            Integer id;
            String teamName;
            String website;
            String nameOfGroup;
            Integer playedMatches;
            Integer points;
            Integer scoredGoals;
            Integer lostGoals;
            Integer yearOfEstablishment;
            String highestLeague;
            try {
                id = jsonObject.optInt("id");
                teamName = jsonObject.getString("teamName");
                website = jsonObject.getString("website");
                nameOfGroup = jsonObject.getString("nameOfGroup");
                playedMatches = jsonObject.optInt("playedMatches");
                points = jsonObject.optInt("points");
                scoredGoals = jsonObject.optInt("scoredGoals");
                lostGoals = jsonObject.optInt("lostGoals");
                yearOfEstablishment = jsonObject.getInt("yearOfEstablishment");
                highestLeague = jsonObject.getString("highestLeague");
                concreteTeam = new ConcreteTeam(id, teamName, website, nameOfGroup, playedMatches, points, scoredGoals, lostGoals, yearOfEstablishment, highestLeague);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }





            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                setDescription(concreteTeam);
            } else {
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
