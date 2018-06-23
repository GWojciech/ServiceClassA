package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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


public class ClassificationsGroup1Activity extends Fragment {
    private String rankBy = "scorers";
    private List<com.golabek.wkck.serviceclassa.database.models.ConcretePlayer> playerList = new ArrayList<>();
    private View viewVar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classifications_group1, container, false);
        viewVar = view;
        initListeners(view);
        new RankTask().execute((Void) null);
        return view;
    }

    public void initListeners(View view){
        Button button = (Button) view.findViewById(R.id.GoalsOfScorer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rankBy = "scorers";
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        TableLayout tableLayout = getView().findViewById(R.id.tabClassGr1);
                        tableLayout.removeViewsInLayout(1,10);
                    }
                });
                new RankTask().execute((Void) null);
            }
        });
        button = (Button) view.findViewById(R.id.AssistOfScorer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rankBy="assists";
                new Handler().post(new Runnable() {

                    @Override
                    public void run()
                    {
                        TableLayout tableLayout = getView().findViewById(R.id.tabClassGr1);
                        tableLayout.removeViewsInLayout(1,10);
                    }
                });
                new RankTask().execute((Void) null);
            }
        });
    }


    public void createScorersTable(View view ){
        Integer pos = 0;
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tabClassGr1);
        TableRow newTableRow;
        for(com.golabek.wkck.serviceclassa.database.models.ConcretePlayer concretePlayer : playerList){
            pos++;
            newTableRow = new TableRow(getContext());
            newTableRow.setMinimumHeight(50);
            newTableRow.setGravity(Gravity.CENTER_HORIZONTAL);
            if(pos==1){
                newTableRow.setBackgroundColor(Color.GREEN);
            }
            TextView textView = new TextView(getContext());
            textView.setText(pos.toString());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            newTableRow.addView(textView);
            textView = new TextView(tableLayout.getContext());
            textView.setText(concretePlayer.getName());
            textView.setTextSize(10f);
            newTableRow.addView(textView);
            textView = new TextView(tableLayout.getContext());
            textView.setTextSize(10f);
            textView.setText(concretePlayer.getSurname());
            newTableRow.addView(textView);
            textView = new TextView(tableLayout.getContext());
            textView.setTextSize(10f);
            textView.setMaxWidth(50);
            textView.setLines(2);
            textView.setText(concretePlayer.getTeamName());
            newTableRow.addView(textView);
            textView = new TextView(tableLayout.getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(concretePlayer.getGoals().toString());
            newTableRow.addView(textView);
            textView = new TextView(tableLayout.getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(concretePlayer.getAssists().toString());
            final Integer finalI = concretePlayer.getId();
            newTableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlayerInformationActivity.number=finalI;
                    PlayerInformationActivity.returnToClassification = true;
                    Intent i = new Intent(getActivity(), PlayerInformationActivity.class);
                    startActivity(i);
                }
            });
            newTableRow.addView(textView);
            tableLayout.addView(newTableRow);

        }
    }

    public class RankTask extends AsyncTask<Void, Void, Boolean> {

        JSONArray getTableJSONArray() {
            URL url = null;
            try {
                url = new URL(getString(R.string.URL_address) + "/player/get/" + rankBy);
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

                if (connection.getResponseCode() == 200) {

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
            // TODO: attempt authentication against a network service.
            playerList.clear();
            JSONArray tableArray = getTableJSONArray();
            JSONObject jsonObject;
            com.golabek.wkck.serviceclassa.database.models.ConcretePlayer concretePlayer;
            Integer id;
            String name;
            String surname;
            Integer goals;
            Integer assists;
            String teamName;
            for (int i = 0; i < tableArray.length(); i++) {
                try {
                    jsonObject = tableArray.getJSONObject(i);
                    id = jsonObject.optInt("id");
                    name = jsonObject.getString("name");
                    surname = jsonObject.getString("surname");
                    goals = jsonObject.optInt("goals");
                    assists = jsonObject.optInt("assists");
                    teamName = jsonObject.getString("teamName");


                    concretePlayer = new com.golabek.wkck.serviceclassa.database.models.ConcretePlayer(id, name, surname, goals, assists, teamName);
                    playerList.add(concretePlayer);

                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }
            }


            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                createScorersTable(viewVar);
            } else {
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
