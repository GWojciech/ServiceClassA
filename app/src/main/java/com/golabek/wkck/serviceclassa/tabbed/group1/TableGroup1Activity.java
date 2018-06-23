package com.golabek.wkck.serviceclassa.tabbed.group1;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.R;
import com.golabek.wkck.serviceclassa.TeamInformationActivity;
import com.golabek.wkck.serviceclassa.database.models.SeasonTable;
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


public class TableGroup1Activity extends Fragment {


    private View viewVar;
    private List<SeasonTable> listTable = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_group1, container, false);
        viewVar = view;
        new TableTask().execute((Void) null);

        return view;
    }

    public void createSeasonTable(View view ){
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tableLayout1);
        SeasonTable seasonTable = null;
        TableRow newTableRow;
        for(Integer i=0; i<12; i++){
            seasonTable = listTable.get(i);
            newTableRow = new TableRow(view.getContext());
            newTableRow.setMinimumHeight(50);
            newTableRow.setGravity(Gravity.CENTER_HORIZONTAL);
            if(i==0){
                newTableRow.setBackgroundColor(Color.GREEN);
            }
            else if(i==10 || i==11){
                newTableRow.setBackgroundColor(Color.RED);
            }
            TextView textView = new TextView(view.getContext());
            textView.setText(seasonTable.getPos().toString());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            newTableRow.addView(textView);
            textView = new TextView(view.getContext());
            textView.setText(seasonTable.getName());
            textView.setTextSize(12f);
            newTableRow.addView(textView);
            textView = new TextView(view.getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(seasonTable.getPlayedMatches().toString());
            newTableRow.addView(textView);
            textView = new TextView(view.getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(seasonTable.getPoints().toString());
            newTableRow.addView(textView);
            textView = new TextView(view.getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(seasonTable.getScoredGoals().toString());
            newTableRow.addView(textView);
            textView = new TextView(view.getContext());
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(seasonTable.getLostGoals().toString());
            final Integer finalI = i;
            newTableRow.setOnClickListener(new View.OnClickListener() {
                SeasonTable seasonTable;
                @Override
                public void onClick(View view) {
                    seasonTable = listTable.get(finalI);
                    TeamInformationActivity.id = seasonTable.getId();
                    TeamInformationActivity.returnToTable=true;
                    Intent i = new Intent(getActivity(), TeamInformationActivity.class);
                    startActivity(i);
//                    ((Activity) getActivity()).overridePendingTransition(0,0);
                }
            });
            newTableRow.addView(textView);
            tableLayout.addView(newTableRow);

        }
    }

    public class TableTask extends AsyncTask<Void, Void, Boolean> {

        JSONArray getTableJSONArray(){
            URL url = null;
            try {
                url = new URL(getString(R.string.URL_address)+"/table/get");
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
            listTable.clear();
            JSONArray tableArray = getTableJSONArray();
            JSONObject jsonObject;
            SeasonTable seasonTable;
            Integer id;
            Integer pos;
            String name;
            Integer playedMatches;
            Integer points;
            Integer scoredGoals;
            Integer lostGoals;
            for(int i=0; i<tableArray.length(); i++){
                try {
                    jsonObject = tableArray.getJSONObject(i);
                    id = jsonObject.optInt("id");
                    lostGoals = jsonObject.optInt("lostGoals");
                    name = jsonObject.getString("name");
                    playedMatches = jsonObject.optInt("playedMatches");
                    points = jsonObject.optInt("points");
                    pos = jsonObject.optInt("pos");
                    scoredGoals = jsonObject.optInt("scoredGoals");
                    seasonTable= new SeasonTable(id, pos, name, playedMatches, points, scoredGoals, lostGoals);
                    listTable.add(seasonTable);


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
                createSeasonTable(viewVar);
            } else {
            }
        }

        @Override
        protected void onCancelled() {

        }
    }





}
