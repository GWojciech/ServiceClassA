package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.golabek.wkck.serviceclassa.database.models.Matches;
import com.golabek.wkck.serviceclassa.helperforviews.ScheudleAdapter;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheudleGroup1Activity extends Fragment {
    private Integer matchday = 0;
    private Integer settingMatchday =0;
    private boolean set = true;
    private List <Matches> matchesList = new ArrayList<>();
    private View viewVar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scheudle_group1, container, false);
        initButtonListeners(view);
        viewVar = view;
        new ScheduleTask().execute((Void) null);

        return  view;
    }

    private void initButtonListeners(View view) {

        Button button = (Button) view.findViewById(R.id.previousMatchdayScheudle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(settingMatchday>matchday){
                    settingMatchday--;
                }
            }
        });

        button = (Button) view.findViewById(R.id.nextMatchdayScheudle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(settingMatchday<=22){
                    settingMatchday++;
                }
            }
        });

    }

    private void fillListView(View view) {
                TextView textView = view.findViewById(R.id.scheudleTextView);
                textView.setText(settingMatchday.toString()+ ". kolejka");
                ListView listView = view.findViewById(R.id.listViewScheudle);
                ScheudleAdapter scheudleAdapter = new ScheudleAdapter(view.getContext(), R.layout.scheudle_info, (ArrayList<Matches>) matchesList);
                listView.setAdapter(scheudleAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Calendar cal = Calendar.getInstance();
                        Matches match = matchesList.get(i);
                        Intent intent = new Intent(Intent.ACTION_EDIT);
                        intent.setType("vnd.android.cursor.item/event");
                        String dateString = match.getDateOfMatch();
                        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = null;
                        try {
                            date = format.parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        date.setHours(15);
                        intent.putExtra("beginTime", date.getTime());
                        intent.putExtra("allDay", false);
                        intent.putExtra("endTime", date.getTime()+120*60*1000);
                        intent.putExtra("title", match.getHomeTeam()+" vs " + match.getAwayTeam());
                        startActivity(intent);
                    }
                });
            }

    public class ScheduleTask extends AsyncTask<Void, Void, Boolean> {

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
                        matchday = Integer.parseInt(text.toString())+1;
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

        JSONArray getSchedule(){
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
            JSONArray matchesArray = getSchedule();
            Matches matches;
            JSONObject jsonObject;
            Integer id;
            String homeTeamName;
            String awayTeamName;
            String date;
            for(int i=0; i<matchesArray.length(); i++){
                try {
                    jsonObject = matchesArray.getJSONObject(i);
                    id = jsonObject.optInt("matchId");
                    homeTeamName = jsonObject.getString("homeTeam");
                    awayTeamName = jsonObject.getString("awayTeam");
                    date = jsonObject.getString("dateOfMatch");
                    matches= new Matches(id, date, homeTeamName, awayTeamName, false);
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
