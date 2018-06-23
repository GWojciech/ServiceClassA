package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
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

import com.golabek.wkck.serviceclassa.MatchesInformation;
import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.database.models.Matches;
import com.golabek.wkck.serviceclassa.helperforviews.ResultsAdapter;
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

public class ResultsGroup1Activity extends Fragment {
    private Integer matchday = 0, settingMatchday=0;
    private List<Matches> matchesList = new ArrayList<>();
    private View viewVar;
    private boolean set = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_group1, container, false);
        initButtonListeners(view);
        viewVar = view;
        new Results().execute((Void) null);

        return  view;
    }

    private void fillListView(View view){
        ListView listView = view.findViewById(R.id.listViewResultsGroup1);
        ResultsAdapter resultsAdapter = new ResultsAdapter(view.getContext(), R.layout.result_info, (ArrayList<Matches>) matchesList);
        listView.setAdapter(resultsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        MatchesInformation.matchToShow = matchesList.get(i);
                        Log.d("UWAGA!", matchesList.get(i).toString());
                        startActivity(new Intent(getActivity(), MatchesInformation.class));
                        getActivity().finishAfterTransition();
                    }
                });

    }

    private void initButtonListeners(View view) {
        final TextView textView = view.findViewById(R.id.resultsTextView);
        Button button = (Button) view.findViewById(R.id.previousMatchdayResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(settingMatchday>1){
                    settingMatchday--;
                    textView.setText(settingMatchday.toString()+". kolejka");
                    new Results().execute((Void) null);
                }
            }
        });

        button = (Button) view.findViewById(R.id.nextMatchdayResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(settingMatchday<matchday){
                    settingMatchday++;
                    textView.setText(settingMatchday.toString()+ ". kolejka");
                    new Results().execute((Void) null);
                }
            }
        });

    }

    public class Results extends AsyncTask<Void, Void, Boolean> {

        void setCurrentMatchday() {
            if(set) {
                URL url = null;
                try {
                    url = new URL(getString(R.string.URL_address) + "/results/get");
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

                    if (connection.getResponseCode() == 200) {
                        connection.disconnect();
                        text = text.replace("\n", "").replace("\r", "");
                        matchday = Integer.parseInt(text.toString());
                        settingMatchday = matchday;
                        set = false;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }

        JSONArray getResults(){
            URL url = null;
            try {
                url = new URL(getString(R.string.URL_address)+"/results/get/"+ settingMatchday.toString());
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
            // TODO: attempt authentication against a network service.
            setCurrentMatchday();
            matchesList.clear();
            JSONArray matchesArray = getResults();
            Matches matches;
            JSONObject jsonObject;
            Integer id;
            String homeTeamName;
            String awayTeamName;
            Integer homeTeamGoals;
            Integer awayTeamGoals;
            String date;
            for(int i=0; i<matchesArray.length(); i++){
                try {
                jsonObject = matchesArray.getJSONObject(i);
                id = jsonObject.optInt("matchId");
                homeTeamName = jsonObject.getString("homeTeam");
                awayTeamName = jsonObject.getString("awayTeam");
                homeTeamGoals = jsonObject.optInt("homeTeamGoals");
                awayTeamGoals = jsonObject.optInt("awayTeamGoals");
                date = jsonObject.getString("dateOfMatch");
                matches= new Matches(id, date, homeTeamName, awayTeamName, true, homeTeamGoals, awayTeamGoals);
                matchesList.add(matches);


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
                fillListView(viewVar);
            } else {
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
