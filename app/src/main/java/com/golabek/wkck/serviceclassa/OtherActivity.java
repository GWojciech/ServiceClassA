package com.golabek.wkck.serviceclassa;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.golabek.wkck.serviceclassa.database.models.ConcretePlayer;
import com.golabek.wkck.serviceclassa.database.models.ConcreteTeam;
import com.golabek.wkck.serviceclassa.helperforviews.SearchPlayerAdapter;
import com.golabek.wkck.serviceclassa.helperforviews.SearchTeamAdapter;
import com.golabek.wkck.serviceclassa.services.InputStreamStringConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class OtherActivity extends AppCompatActivity {
    private String searching = "player/get";
    private List <ConcretePlayer> playerList = new ArrayList<>();
    private List <ConcreteTeam> teamList = new ArrayList<>();

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
                            searching = "/player/get";
                            new TeamsOrPlayersTask().execute((Void) null);
                            break;

                        case "Drużyna":
                            searching = "/team/get";
                            new TeamsOrPlayersTask().execute((Void) null);
                            break;
                    }
                }

            }
        });
    }

    private void displayListOfPlayers(){
        final SearchPlayerAdapter searchPlayerAdapter = new SearchPlayerAdapter(getApplicationContext(), R.layout.search_info, (ArrayList<ConcretePlayer>) playerList);
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
                PlayerInformationActivity.number=playerList.get(i).getId();
                PlayerInformationActivity.returnToClassification = false;

                startActivity(new Intent(OtherActivity.this, PlayerInformationActivity.class));
                finishAfterTransition();
            }
        });

    }

    private void displayListOfTeams(){
        final SearchTeamAdapter searchTeamAdapter = new SearchTeamAdapter(this, R.layout.search_info, (ArrayList<ConcreteTeam>) teamList);
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
                TeamInformationActivity.id = teamList.get(i).getId();
                TeamInformationActivity.returnToTable=false;
                startActivity(new Intent(OtherActivity.this, TeamInformationActivity.class));
                finishAfterTransition();
            }
        });

    }

    public class TeamsOrPlayersTask extends AsyncTask<Void, Void, Boolean> {

        JSONArray getJSONArray(){
            URL url = null;
            try {
                url = new URL(getString(R.string.URL_address)+ searching);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(false);
                connection.setDoInput(true);
                JSONArray jsonArray;
                InputStream in = new BufferedInputStream(connection.getInputStream());
                String text = InputStreamStringConverter.streamToString(in);
                in.close();

                if(connection.getResponseCode()==200) {

                    jsonArray = new JSONArray(text);
                    connection.disconnect();
                    return jsonArray;
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
            playerList.clear();
            teamList.clear();
            // TODO: attempt authentication against a network service.
            JSONArray jsonArray = getJSONArray();
            JSONObject jsonObject;
            if(searching.equals("/player/get")) {
                com.golabek.wkck.serviceclassa.database.models.ConcretePlayer concretePlayer;
                Integer id;
                String name;
                String surname;
                String birthDate;
                Integer goals;
                Integer assists;
                String website;
                String teamName;
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        jsonObject = jsonArray.getJSONObject(i);
                        id = jsonObject.optInt("id");
                        name = jsonObject.getString("name");
                        surname = jsonObject.getString("surname");
                        birthDate = jsonObject.getString("birthDate");
                        goals = jsonObject.optInt("goals");
                        assists = jsonObject.optInt("assists");
                        website = jsonObject.getString("website");
                        teamName = jsonObject.getString("teamName");
                        concretePlayer = new ConcretePlayer(id, name, surname, birthDate, goals, assists, website, teamName);
                        playerList.add(concretePlayer);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
                else {
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
                com.golabek.wkck.serviceclassa.database.models.ConcreteTeam concreteTeam;
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        jsonObject = jsonArray.getJSONObject(i);
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
                        teamList.add(concreteTeam);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        return false;
                    }

                }
            }





            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                if(searching.equals("/player/get")) {
                    displayListOfPlayers();
                }
                else
                    displayListOfTeams();
            } else {
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
